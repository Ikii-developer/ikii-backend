package mx.ikii.payment.methods.conekta.service.orders;

import io.conekta.Order;
import mx.ikii.payment.payload.request.OrderConektaRequest;
import mx.ikii.payment.payload.request.RefoundOrderRequest;

public interface IOrdersConektaService {
	
	Order createOrderWithCardCharge(OrderConektaRequest orderConektaRequest);
	
	Order createAnOrderWithAnOXXOPayCharge(OrderConektaRequest orderConektaRequest);
	
	Order createAnOrderWithSPEIcharge(OrderConektaRequest orderConektaRequest);
	
	Order updateOrder(OrderConektaRequest orderConektaRequest);
	
	Order findOrder(String orderId);
	
	void captureOrder(String orderId);
	
	Order refoundOrder(RefoundOrderRequest refoundOrderRequest);

}
