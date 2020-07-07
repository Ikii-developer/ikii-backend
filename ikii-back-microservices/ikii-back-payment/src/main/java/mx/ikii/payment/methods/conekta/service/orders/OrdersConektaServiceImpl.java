package mx.ikii.payment.methods.conekta.service.orders;

import java.math.BigDecimal;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.conekta.Order;
import lombok.extern.slf4j.Slf4j;
import mx.ikii.payment.methods.conekta.repository.orders.IOrdersConektaRepository;
import mx.ikii.payment.payload.request.OrderConektaRequest;
import mx.ikii.payment.payload.request.RefoundOrderRequest;

/**
 * Una orden representa una compra. Incluye todos los detalles relacionados a ella, 
 * 	como método de pago, envío, productos comprados, cargos, descuentos e impuestos.
 * 
 * https://developers.conekta.com/api?language=java#order
 */
@Slf4j
@Service
public class OrdersConektaServiceImpl implements IOrdersConektaService {
	
	@Autowired
	private IOrdersConektaRepository ordersConektaRepository;

	@Override
	public Order createOrderWithCardCharge(OrderConektaRequest orderConektaRequest) {
		orderConektaRequest.getLine_items().forEach(item->{
			//unit_price: El precio por unidad expresado en centavos.
			item.setUnit_price(item.getUnit_price().multiply(new BigDecimal(100)));
		});
//        "'charges': [{" +
//        "    'payment_method': {" +
//        "        'type': 'card'," +
//        "        'token_id': 'tok_test_visa_4242'" +
//        "    }, " +
//        "    'amount': 35000" +
//        "}]" +
		Order order = ordersConektaRepository.createOrder(new JSONObject(orderConektaRequest));
		//Okta specific business logic
		return order;
	}
	
	@Override
	public Order createAnOrderWithAnOXXOPayCharge(OrderConektaRequest orderConektaRequest) {
		orderConektaRequest.getLine_items().forEach(item->{
			//unit_price: El precio por unidad expresado en centavos.
			item.setUnit_price(item.getUnit_price().multiply(new BigDecimal(100)));
		});
		
//        "'charges': [{" +
//        "    'payment_method': {" +
//        "        'type': 'oxxo_cash'" +
//        "    }, " +
//        "    'amount': 35000" +
//        "}]" +
		Order order = ordersConektaRepository.createOrder(new JSONObject(orderConektaRequest));
		//Okta specific business logic
		return order;
	}
	
	@Override
	public Order createAnOrderWithSPEIcharge(OrderConektaRequest orderConektaRequest) {
		orderConektaRequest.getLine_items().forEach(item->{
			//unit_price: El precio por unidad expresado en centavos.
			item.setUnit_price(item.getUnit_price().multiply(new BigDecimal(100)));
		});
		
//        "'charges': [{" +
//        "    'payment_method': {" +
//        "        'type': 'spei'" +
//        "    }, " +
//        "    'amount': 35000" +
//        "}]" +
		Order order = ordersConektaRepository.createOrder(new JSONObject(orderConektaRequest));
		//Okta specific business logic
		return order;
	}
	
	
	@Override
	public Order updateOrder(OrderConektaRequest orderConektaRequest) {
//		JSONObject data = new JSONObject("{'currency':'MXN'}");
		
		Order order = ordersConektaRepository.updateOrder(new JSONObject(orderConektaRequest));
		//Okta specific business logic
		return order;
	}

	@Override
	public Order findOrder(String orderId) {
		Order order = ordersConektaRepository.findOrder(orderId);
		//Okta specific business logic
		return order;
	}
	
	@Override
	public void captureOrder(String orderId) {
		//Procesa una orden que ha sido previamente autorizada.
		
		ordersConektaRepository.captureOrder(orderId);
	}
	
	@Override
	public Order refoundOrder(RefoundOrderRequest refoundOrderRequest) {
		Order order = findOrder(refoundOrderRequest.getOrder_id());
		refoundOrderRequest.setOrder_id(null);
		return ordersConektaRepository.refoundOrder(order, new JSONObject(refoundOrderRequest));
	}

}
