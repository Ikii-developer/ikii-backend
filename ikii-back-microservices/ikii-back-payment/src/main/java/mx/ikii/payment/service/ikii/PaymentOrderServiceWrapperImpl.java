package mx.ikii.payment.service.ikii;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import io.conekta.Order;
import lombok.extern.slf4j.Slf4j;
import mx.ikii.commons.domain.OrderStatus;
import mx.ikii.commons.domain.OrderSubStatus;
import mx.ikii.commons.exception.handler.ConektaRepositoryException;
import mx.ikii.commons.exception.handler.helper.ConflictException;
import mx.ikii.commons.feignclient.service.impl.ICustomerDetailsFeignService;
import mx.ikii.commons.mapper.order.IPaymentOrderMapper;
import mx.ikii.commons.payload.dto.OrderSubstatusDetail;
import mx.ikii.commons.payload.dto.PaymentOrderDTO;
import mx.ikii.commons.payload.request.order.OrderDeliveryTimeRequest;
import mx.ikii.commons.payload.request.order.OrderDetailRequest;
import mx.ikii.commons.payload.request.order.OrderFilter;
import mx.ikii.commons.payload.request.order.OrderRequest;
import mx.ikii.commons.payload.request.order.OrderStatusRequest;
import mx.ikii.commons.payload.response.payment.conekta.OrderConektaResponse;
import mx.ikii.commons.payload.response.payment.order.PaymentOrderResponse;
import mx.ikii.commons.persistence.collection.CustomerDetails;
import mx.ikii.commons.persistence.collection.OrderDetail;
import mx.ikii.commons.persistence.collection.PaymentOrder;
import mx.ikii.commons.persistence.collection.util.ProductDetail;
import mx.ikii.commons.utils.Nullable;
import mx.ikii.payment.mapper.OrderConektaMapper;
import mx.ikii.payment.methods.conekta.service.orders.IOrdersConektaService;
import mx.ikii.payment.payload.request.OrderConektaRequest;
import mx.ikii.payment.payload.request.RefoundOrderRequest;

@Slf4j
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

  @Autowired
  private ICustomerDetailsFeignService customerDetailsFeignService;

  @Transactional
  @Override
  public PaymentOrderResponse order(OrderRequest orderRequest) {
    log.info("[PaymentOrderServiceWrapperImpl] - INIT create order with request [{}] ",
        orderRequest);
    String customerId = orderRequest.getCostumerId();
    CustomerDetails customerDetails =
        customerDetailsFeignService.findByCustomerId(customerId);

    List<ProductDetail> productDetails = this.getProductDetails(orderRequest.getOrderDetails());
    OrderDetail orderDetail = OrderDetail.builder()
        .products(productDetails)
        .businessId(new ObjectId(orderRequest.getBusinessId()))
        .build();
    orderDetailService.save(orderDetail);

    PaymentOrder paymentOrder = PaymentOrder.builder()
        .orderDetail(orderDetail)
        .customerId(new ObjectId(orderRequest.getCostumerId()))
        .createdAt(LocalDateTime.now())
        .customerIdConekta(customerDetails.getCustomerConektaId())
        .paymentMethod(orderRequest.getPaymentMethod())
        .orderType(orderRequest.getOrderType())
        .orderNumber(this.generateOrderNumber())
        .build();
    paymentOrderService.calculateTotals(paymentOrder, orderDetail);

    OrderConektaResponse orderConektaResponse = null;
    try {
      OrderConektaRequest orderConektaRequest =
          OrderConektaMapper.paymentOrderToOrderConektaRequest(paymentOrder, orderDetail);
      Order orderConekta = ordersConektaService.createOrderWithCardCharge(orderConektaRequest);
      orderConektaResponse = OrderConektaMapper.orderConektaToOrderConektaResponse(orderConekta);
      paymentOrder.setStatus(OrderStatus.ACCEPTED.name());
      paymentOrder.setTransactionId(orderConektaResponse.getId());
      paymentOrder.setOrderSubstatusDetail(OrderSubstatusDetail.builder()
          .date(LocalDateTime.now())
          .subStatus(OrderSubStatus.REQUESTED)
          .build());
    } catch (ConektaRepositoryException ex) {
      paymentOrder.setStatus(OrderStatus.ERROR.name());
      paymentOrder.setMessage(ex.getMessage());
      paymentOrder.setOrderSubstatusDetail(OrderSubstatusDetail.builder()
          .date(LocalDateTime.now())
          .subStatus(OrderSubStatus.ERROR)
          .description(ex.getMessage())
          .build());
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
    // Order orderConekta = ordersConektaService.findOrder(paymentOrder.getTransactionId());
    return paymentOrderMapper.entityToResponse(paymentOrder);
  }

  @Override
  public PaymentOrderResponse refund(OrderRequest orderRequest) {
    PaymentOrder paymentOrder = paymentOrderService.getById(orderRequest.getIdOrder());

    RefoundOrderRequest refoundOrderRequest = new RefoundOrderRequest();
    refoundOrderRequest.setOrder_id(paymentOrder.getTransactionId());
    refoundOrderRequest.setReason(orderRequest.getReasonRefund());

    Order orderConekta = ordersConektaService.refoundOrder(refoundOrderRequest);
    paymentOrder.setStatus(OrderStatus.REFUNDED.name());
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

  private PaymentOrderResponse getPaymentOrderResponse(PaymentOrder paymentOrder,
      Order orderConekta) {
    PaymentOrderResponse paymentOrderResponse = paymentOrderMapper.entityToResponse(paymentOrder);
    OrderConektaResponse orderConektaResponse =
        OrderConektaMapper.orderConektaToOrderConektaResponse(orderConekta);
    paymentOrderResponse.setOrderConektaResponse(orderConektaResponse);
    return paymentOrderResponse;
  }

  @Override
  public void updateOrderIkiiStatus(String orderId, OrderStatusRequest orderStatusRequest) {
    PaymentOrder paymentOrder = paymentOrderService.getById(orderId);

    OrderSubstatusDetail orderSubstatusDetail = paymentOrder.getOrderSubstatusDetail();
    List<OrderSubstatusDetail> orderSubstatusHistory = orderSubstatusDetail.getSubStatusHistory();
    if (Nullable.isNullOrEmpty(orderSubstatusHistory))
      orderSubstatusHistory = new ArrayList<>();
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
  public void updateOrderDeliveryTime(String orderId, OrderDeliveryTimeRequest orderStatusRequest) {
    PaymentOrder paymentOrder = paymentOrderService.getById(orderId);
    if (!paymentOrder.getOrderSubstatusDetail().getSubStatus().equals(OrderSubStatus.ON_DELIVERY)) {
      throw new ConflictException(String.format(
          "The order can not be update because it is not in delivery status[orderId: %s] ",
          orderId));
    }
    LocalDateTime arriveTime = LocalDateTime.now(ZoneId.of("America/Mexico_City"));
    arriveTime = arriveTime.plusMinutes(orderStatusRequest.getMinutesToArrive());
    paymentOrder.setArrivedTime(arriveTime);
    paymentOrderService.update(paymentOrder);
  }

  @Override
  public List<PaymentOrderDTO> filter(OrderFilter orderFilter) {
    return paymentOrderService.filter(orderFilter);
  }

  @Override
  public List<PaymentOrderResponse> getByCustomerId(String customerId) {
    return paymentOrderMapper.entityToResponse(paymentOrderService.getByCustomerId(customerId));
  }

  private Integer generateOrderNumber() {
    Random r = new Random();
    int low = 1000;
    int high = 100000;
    int result = r.nextInt(high - low) + low;
    return result;

  }

}
