package mx.ikii.payment.service.order;

import mx.ikii.payment.payload.request.OrderConektaRequest;
import mx.ikii.payment.payload.request.RefoundOrderRequest;
import mx.ikii.payment.payload.response.OrderConektaResponse;

public interface IKiiPaymentOrderServiceWrapper {

	OrderConektaResponse createOrderWithCardCharge(OrderConektaRequest orderRequestt);
	
	OrderConektaResponse updateOrderConekta(OrderConektaRequest orderConektaRequest);
	
	OrderConektaResponse findOrderConekta(String orderId);
	
	void orderAuthorizeCapture(String orderId);
	
	void refoundOrderConekta(RefoundOrderRequest refoundOrderRequest);
	
}