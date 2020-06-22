package mx.ikii.payment.conekta.repository.orders;

import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.conekta.Error;
import io.conekta.ErrorList;
import io.conekta.Order;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrdersConektaRepositoryImpl implements IOrdersConektaRepository {
	
	@Value("${conekta.api-key}")
	private String apiKey;

	@Value("${conekta.api-version}")
	private String apiVersion;

	
	@Override
	public Optional<Order> createOrder(JSONObject orderRequestJSON) {
		Optional<Order> completeOrder = Optional.empty();
		try {
			completeOrder = Optional.ofNullable(Order.create(orderRequestJSON));
		} catch (ErrorList | Error e) {
			log.info(e.getMessage());
			e.printStackTrace();
			// TODO: throw CustomError
		}
		
		return completeOrder;
	}

	@Override
	public Optional<Order> updateOrder(JSONObject orderRequestJSON) {
		Optional<Order> updatedOrder = findOrder(orderRequestJSON.getString("id"));
		// TODO: throw CustomError if Order is empty
		try {
			updatedOrder.get().update(orderRequestJSON);
		} catch (Error | ErrorList e) {
			log.info(e.getMessage());
			e.printStackTrace();
			// TODO: throw CustomError
		}
		return updatedOrder;
	}

	@Override
	public Optional<Order> findOrder(String orderId) {
		Optional<Order> order = Optional.empty();
		try {
			order = Optional.ofNullable(Order.find(orderId));
		} catch (ErrorList | Error e) {
			log.info(e.getMessage());
			e.printStackTrace();
			// TODO: throw CustomError
		}
		
		return order;
	}
	
	@Override
	public void captureOrder(String orderId) {
		Optional<Order> order = findOrder(orderId);
		// TODO: throw CustomError if Order is empty
		try {
			order.get().capture();
		} catch (ErrorList | Error e) {
			log.info(e.getMessage());
			e.printStackTrace();
			// TODO: throw CustomError
		}
	}
	
	@Override
	public Optional<Order> refoundOrder(JSONObject orderRefoundJSON) {
		Optional<Order> order = findOrder(orderRefoundJSON.getString("orderId"));
		// TODO: throw CustomError if Order is empty
		try {
			order.get().refund(orderRefoundJSON);
		} catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
			// TODO: throw CustomError
		}
		return order;
	}

	@Override
	public void deleteOrder(String orderId) {
		Optional<Order> order = findOrder(orderId);
		// TODO: throw CustomError Commons
		try {
			order.get().delete();
		} catch (Error | ErrorList e) {
			log.info(e.getMessage());
			e.printStackTrace();
			// TODO: throw CustomError Commons
		}
	}

}
