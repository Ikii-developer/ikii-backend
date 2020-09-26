package mx.ikii.payment.service.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.conekta.Order;
import mx.ikii.commons.payload.response.payment.conekta.OrderConektaResponse;
import mx.ikii.commons.persistence.collection.PaymentOrderInfo;
import mx.ikii.payment.mapper.OrderConektaMapper;
import mx.ikii.payment.methods.conekta.service.orders.IOrdersConektaService;
import mx.ikii.payment.payload.request.OrderConektaRequest;
import mx.ikii.payment.payload.request.RefoundOrderRequest;
import mx.ikii.payment.service.ikii.IkiiPaymentOrderInfoService;

@Service
public class IkiiPaymentOrderServiceWrapperImpl implements IKiiPaymentOrderServiceWrapper {
	
//	@Autowired
//	private IOrderOpenPayService orderOpenPayService;
	
	@Autowired
	private IOrdersConektaService ordersConectaService;
	
	@Autowired
	private IkiiPaymentOrderInfoService ikiiPaymentOrderInfoService;

	@Override
	public OrderConektaResponse createOrderWithCardCharge(OrderConektaRequest orderConektaRequest) {
//		orderConektaRequest.getLine_items().forEach(c-> {
//			if(new BigDecimal(5000).compareTo(c.getUnit_price()) == -1) {
//				throw new RuntimeException("You're probably a hacker");
//			}
//		});
		
		Order order = ordersConectaService.createOrderWithCardCharge(orderConektaRequest);
		OrderConektaResponse response = OrderConektaMapper.orderConektaToOrderConektaResponse(order);
		
		//Change To Mapper
		PaymentOrderInfo paymentOrderInfo = new PaymentOrderInfo();
		paymentOrderInfo.setCustomerConektaId(response.getCustomer_info().getCustomer_id());
		paymentOrderInfo.setOrderConektaId(response.getId());
		paymentOrderInfo.setPaymentStatus(response.getPayment_status());
		ikiiPaymentOrderInfoService.create(paymentOrderInfo);
		
		return response;
	}

	@Override
	public OrderConektaResponse updateOrderConekta(OrderConektaRequest orderConektaRequest) {
//		orderConektaRequest.getLine_items().forEach(c-> {
//			if(new BigDecimal(5000).compareTo(c.getUnit_price()) == -1) {
//				throw new RuntimeException("You're probably a hacker");
//			}
//		});
		
		Order order = ordersConectaService.updateOrder(orderConektaRequest);
		OrderConektaResponse response = OrderConektaMapper.orderConektaToOrderConektaResponse(order);
		
		//Change To Mapper
//		PaymentOrderInfo paymentOrderInfo = ikiiPaymentOrderInfoService.findById(null);
//		paymentOrderInfo.setCustomerConektaId(response.getCustomer_info().getCustomer_id());
//		paymentOrderInfo.setOrderConektaId(response.getId());
//		paymentOrderInfo.setPaymentStatus(response.getPayment_status());
//		ikiiPaymentOrderInfoService.update(paymentOrderInfo);
		
		return response;
	}

	@Override
	public OrderConektaResponse findOrderConekta(String orderId) {
		Order order = ordersConectaService.findOrder(orderId);
		OrderConektaResponse response = OrderConektaMapper.orderConektaToOrderConektaResponse(order);
		return response;
	}
	
	@Override
	public void orderAuthorizeCapture(String orderId) {
		//Procesa una orden que ha sido previamente autorizada.
		ordersConectaService.captureOrder(orderId);
	}

	@Override
	public void refoundOrderConekta(RefoundOrderRequest refoundOrderRequest) {
		ordersConectaService.refoundOrder(refoundOrderRequest);
	}

}
