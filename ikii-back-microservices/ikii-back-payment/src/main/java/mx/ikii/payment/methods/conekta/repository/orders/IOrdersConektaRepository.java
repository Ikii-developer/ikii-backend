package mx.ikii.payment.methods.conekta.repository.orders;

import org.json.JSONObject;

import io.conekta.Order;

public interface IOrdersConektaRepository {
	
	Order findOrder(String orderId);
	
	Order createOrder(JSONObject orderRequestJSON);

	Order updateOrder(JSONObject orderRequestJSON);

	void captureOrder(String orderId);

	Order refoundOrder(Order order, JSONObject orderRefoundJSON);
	
	void deleteOrder(String orderId);

}
