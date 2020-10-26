package mx.ikii.payment.service.ikii;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import io.conekta.Order;
import mx.ikii.commons.domain.OrderStatus;
import mx.ikii.commons.domain.OrderSubStatus;
import mx.ikii.commons.exception.handler.ConektaRepositoryException;
import mx.ikii.commons.mapper.order.IPaymentOrderMapper;
import mx.ikii.commons.payload.dto.OrderSubstatusDetail;
import mx.ikii.commons.payload.dto.PaymentOrderDTO;
import mx.ikii.commons.payload.request.order.OrderDetailRequest;
import mx.ikii.commons.payload.request.order.OrderFilter;
import mx.ikii.commons.payload.request.order.OrderRequest;
import mx.ikii.commons.payload.request.order.OrderStatusRequest;
import mx.ikii.commons.payload.response.payment.conekta.OrderConektaResponse;
import mx.ikii.commons.payload.response.payment.order.PaymentOrderResponse;
import mx.ikii.commons.persistence.collection.OrderDetail;
import mx.ikii.commons.persistence.collection.PaymentOrder;
import mx.ikii.commons.persistence.collection.util.ProductDetail;
import mx.ikii.commons.utils.Nullable;
import mx.ikii.payment.mapper.OrderConektaMapper;
import mx.ikii.payment.methods.conekta.service.orders.IOrdersConektaService;
import mx.ikii.payment.payload.request.OrderConektaRequest;
import mx.ikii.payment.payload.request.RefoundOrderRequest;

@Service
@Transactional
public class PaymentOrderServiceWrapperImpl implements IPaymentOrderServiceWrapper {

    @Autowired
    private IOrderDetailService orderDetailService;

    @Autowired
    private IPaymentOrderService paymentOrderService;

    @Autowired
    private IOrdersConektaService ordersConektaService;
    
    @Autowired
    private IPaymentOrderMapper paymentOrderMapper;
    
    // TODO change to pattern Template or chain of responsibility, possible use of RabbitMQ
    @Transactional
    @Override
    public PaymentOrderResponse order(OrderRequest orderRequest) {
        List<ProductDetail> productDetails = this.getProductDetails(orderRequest.getOrderDetails());
        OrderDetail orderDetail = OrderDetail.builder()
                .products(productDetails)
                .businessId(new ObjectId(orderRequest.getBusinessId()))
                .build();
        orderDetailService.save(orderDetail);
        PaymentOrder paymentOrder = PaymentOrder.builder()
                .orderDetailId(new ObjectId(orderDetail.getId()))
                .customerId(new ObjectId(orderRequest.getCostumerId()))
                .createdAt(LocalDateTime.now())
                .customerIdConekta(orderRequest.getCostumerIdConekta())
                .paymentMethod(orderRequest.getPaymentMethod())
                .orderType(orderRequest.getOrderType())
                .build();
        paymentOrderService.calculateTotals(paymentOrder, orderDetail);
        OrderConektaResponse orderConektaResponse = null;
        try {
            OrderConektaRequest orderConektaRequest = OrderConektaMapper.paymentOrderToOrderConektaRequest(paymentOrder, orderDetail);          
            Order orderConekta = ordersConektaService.createOrderWithCardCharge(orderConektaRequest);
            orderConektaResponse = OrderConektaMapper.orderConektaToOrderConektaResponse(orderConekta);
            paymentOrder.setStatus(OrderStatus.ACCEPTED.getStatus());
            paymentOrder.setTransactionId(orderConektaResponse.getId());
            paymentOrder.setOrderSubstatusDetail(new OrderSubstatusDetail(OrderSubStatus.REQUESTED, LocalDateTime.now()));
            // TODO will need to process to the delivery step
        }catch(ConektaRepositoryException ex) {
            paymentOrder.setStatus(OrderStatus.ERROR.getStatus());
            paymentOrder.setMessage(ex.getMessage());
        }
        paymentOrder = paymentOrderService.save(paymentOrder);
        PaymentOrderResponse paymentOrderResponse = paymentOrderMapper.entityToResponse(paymentOrder);
        paymentOrderResponse.setOrderConektaResponse(orderConektaResponse);
        return paymentOrderResponse;
    }



    @Override
    public PaymentOrderResponse update(OrderRequest orderRequest) {
        PaymentOrder paymentOrder = paymentOrderService.getById(orderRequest.getIdOrder());
        paymentOrder.setStatus(orderRequest.getStatus());
        paymentOrder = paymentOrderService.update(paymentOrder);
        Order orderConekta = ordersConektaService.findOrder(paymentOrder.getTransactionId());
        return this.getPaymentOrderResponse(paymentOrder, orderConekta);
    }
    
    @Override
    public PaymentOrderResponse getById(String id) {
        PaymentOrder paymentOrder = paymentOrderService.getById(id);
        Order orderConekta = ordersConektaService.findOrder(paymentOrder.getTransactionId());
        return this.getPaymentOrderResponse(paymentOrder, orderConekta);
    }

    @Override
    public PaymentOrderResponse refund(OrderRequest orderRequest) {
        PaymentOrder paymentOrder = paymentOrderService.getById(orderRequest.getIdOrder());
        
        RefoundOrderRequest refoundOrderRequest = new RefoundOrderRequest();
        refoundOrderRequest.setOrder_id(paymentOrder.getTransactionId());
        refoundOrderRequest.setReason(orderRequest.getReasonRefund());
        
        Order orderConekta = ordersConektaService.refoundOrder(refoundOrderRequest);
        paymentOrder.setStatus(OrderStatus.REFUNDED.getStatus());
        paymentOrder.setReasonRefund(orderRequest.getReasonRefund());
        paymentOrderService.update(paymentOrder);
        
        return this.getPaymentOrderResponse(paymentOrder, orderConekta);
    }
    
    private List<ProductDetail> getProductDetails(List<OrderDetailRequest> orderDetails) {
        return orderDetails.stream().map(od -> {
            ProductDetail productDetail = new ProductDetail();
            productDetail.setTotalAmount(od.getAmount().multiply(od.getProductPrice()));
            productDetail.setUnitAmmount(od.getAmount());
            productDetail.setProductDescription(od.getProductDescription());
            productDetail.setProductName(od.getProductName());
            productDetail.setProductId(od.getProductId());
            productDetail.setProductPrice(od.getProductPrice());
            return productDetail;
        }).collect(Collectors.toList());
    }
    
    
    private PaymentOrderResponse getPaymentOrderResponse(PaymentOrder paymentOrder, Order orderConekta ) {
        PaymentOrderResponse paymentOrderResponse = paymentOrderMapper.entityToResponse(paymentOrder);
        OrderConektaResponse orderConektaResponse = OrderConektaMapper.orderConektaToOrderConektaResponse(orderConekta);
        paymentOrderResponse.setOrderConektaResponse(orderConektaResponse);
        return paymentOrderResponse;
    }


    @Override
    public void updateOrderIkiiStatus(String orderId, OrderStatusRequest orderStatusRequest) {
        PaymentOrder paymentOrder = paymentOrderService.getById(orderId);

        OrderSubstatusDetail orderSubstatusDetail = paymentOrder.getOrderSubstatusDetail();
        List<OrderSubstatusDetail> orderSubstatusHistory = orderSubstatusDetail.getSubStatusHistory();
        if(Nullable.isNullOrEmpty(orderSubstatusHistory)) orderSubstatusHistory = new ArrayList<>();
        orderSubstatusHistory.add(new OrderSubstatusDetail(orderSubstatusDetail.getSubStatus(),
                                    orderSubstatusDetail.getDate(), orderSubstatusDetail.getDescription()));
        orderSubstatusDetail.setSubStatusHistory(orderSubstatusHistory);
        paymentOrder.setOrderSubstatusDetail(orderSubstatusDetail);
        orderSubstatusDetail.setSubStatus(orderStatusRequest.getSubStatus());
        orderSubstatusDetail.setDescription(orderStatusRequest.getDescription());
        orderSubstatusDetail.setDate(LocalDateTime.now());
        paymentOrderService.update(paymentOrder);
    }



  @Override
  public List<PaymentOrderDTO> filter(OrderFilter orderFilter) {
    return paymentOrderService.filter(orderFilter);
  }
    
}
