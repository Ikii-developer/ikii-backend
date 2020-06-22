package mx.ikii.payment.conekta.service.orders;

import io.conekta.Order;
import mx.ikii.payment.payload.request.OrderConektaRequest;

public interface IOrdersConektaService {
	
	Order createOrderWithCardCharge(OrderConektaRequest orderConektaRequest);
	
	Order createAnOrderWithAnOXXOPayCharge(OrderConektaRequest orderConektaRequest);
	
	Order createAnOrderWithSPEIcharge(OrderConektaRequest orderConektaRequest);
	
	Order updateOrder(OrderConektaRequest orderConektaRequest);
	
	Order findOrder(String orderRequestId);
	
	void captureOrder(String orderRequestId);
	
	Order refoundOrder(String orderRequestId);

}
