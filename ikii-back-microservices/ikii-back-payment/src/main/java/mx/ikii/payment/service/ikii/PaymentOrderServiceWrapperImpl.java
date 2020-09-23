package mx.ikii.payment.service.ikii;

import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.ikii.commons.domain.OrderStatus;
import mx.ikii.commons.mapper.order.IPaymentOrderMapper;
import mx.ikii.commons.payload.request.order.OrderDetailRequest;
import mx.ikii.commons.payload.request.order.OrderRequest;
import mx.ikii.commons.payload.response.payment.order.PaymentOrderResponse;
import mx.ikii.commons.persistence.collection.OrderDetail;
import mx.ikii.commons.persistence.collection.PaymentOrder;
import mx.ikii.commons.persistence.collection.util.ProductDetail;
import mx.ikii.payment.methods.conekta.service.payments.IPaymentsConektaService;

@Service
public class PaymentOrderServiceWrapperImpl implements IPaymentOrderServiceWrapper {

	@Autowired
	private IOrderDetailService orderDetailService;

	@Autowired
	private IPaymentOrderService paymentOrderService;

	@Autowired
	private IPaymentsConektaService paymentConektaService;
	
	@Autowired
	private IPaymentOrderMapper paymentOrderMapper;
	
	@Override
	// TODO change to pattern Template or chain of responsibility, possible use of
	// RabbitMQ
	public PaymentOrderResponse order(OrderRequest orderRequest) {
		List<ProductDetail> productDetails = this.getProductDetails(orderRequest.getOrderDetails());
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setProducts(productDetails);
		orderDetail.setBusinessId(new ObjectId(orderRequest.getBusinessId()));
		OrderDetail persitedOrderDetail = orderDetailService.save(orderDetail);
		PaymentOrder paymentOrder = new PaymentOrder();
		paymentOrder.setDetail(persitedOrderDetail);
		paymentOrder.setStatus(OrderStatus.ACCEPTED.getStatus());
		paymentOrder.setCustomerId(new ObjectId(orderRequest.getCostumerId()));
		paymentOrder = paymentOrderService.save(paymentOrder);
		// TODO will need to process to the delivery step
		// TODO get customers methodPayment PaymentSource paymentSource =
		// paymentConektaService.create(paymentOrder.getCustomerId().toHexString(), null);//creating payment
		return paymentOrderMapper.entityToResponse(paymentOrder);
	}

	@Override
	public PaymentOrderResponse update(OrderRequest orderRequest) {
		PaymentOrder paymentOrder = paymentOrderService.getById(orderRequest.getIdOrder());
		List<ProductDetail> productDetails = this.getProductDetails(orderRequest.getOrderDetails());
		paymentOrder.getDetail().setProducts(productDetails);
		paymentOrder.getDetail().setBusinessId(new ObjectId(orderRequest.getBusinessId()));
		OrderDetail persitedOrderDetail = orderDetailService.update(paymentOrder.getDetail());
		paymentOrder.setDetail(persitedOrderDetail);
		paymentOrder.setCustomerId(new ObjectId(orderRequest.getCostumerId()));
		paymentOrder = paymentOrderService.update(paymentOrder);
		return paymentOrderMapper.entityToResponse(paymentOrder);
	}
	
	@Override
	public PaymentOrderResponse getById(String id) {
		PaymentOrder paymentOrder = paymentOrderService.getById(id);
		return paymentOrderMapper.entityToResponse(paymentOrder);
	}

	@Override
	public void delete(String id) {
		PaymentOrder paymentOrder = paymentOrderService.getById(id);
		OrderDetail orderDetail = orderDetailService.getById(paymentOrder.getDetail().getId());
		orderDetailService.delete(orderDetail.getId());
		paymentOrderService.delete(paymentOrder.getId());
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


}
