package mx.ikii.payment.conekta.repository.orders;

import java.util.Optional;

import org.json.JSONObject;

import io.conekta.Order;

public interface IOrdersConektaRepository {
	
	Optional<Order> createOrder(JSONObject orderRequestJSON);

	Optional<Order> updateOrder(JSONObject orderRequestJSON);

	Optional<Order> findOrder(String orderId);
	
	/** 
	 * Capturar Orden
	 * Procesa una orden que ha sido previamente autorizada.
	 */
	void captureOrder(String orderId);

	Optional<Order> refoundOrder(JSONObject orderRefoundJSON);
	
	void deleteOrder(String orderId);

}
