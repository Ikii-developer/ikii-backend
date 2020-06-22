package mx.ikii.payment.conekta.service.orders;

import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.conekta.Order;
import lombok.extern.slf4j.Slf4j;
import mx.ikii.payment.conekta.repository.orders.IOrdersConektaRepository;
import mx.ikii.payment.payload.request.OrderConektaRequest;

/**
 * ===================================================================================
 * =========			ORDERS						==================================
 * ===================================================================================
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
		// TODO settings order for Card Charge
		
//		JSONObject completeOrderJSON = new JSONObject("{" +
//		        "'currency': 'mxn'," +
//		        "'metadata': {" +
//		        "    'test': true"+
//		        "}," +
//		        "'line_items': [{" +
//		        "    'name': 'Box of Cohiba S1s'," +
//		        "    'description': 'Imported From Mex.'," +
//		        "    'unit_price': 35000," +
//		        "    'quantity': 1," +
//		        "    'tags': ['food', 'mexican food']," +
//		        "    'type': 'physical'" +
//		        "}]," +
//		        "'customer_info': { " +
//		        "    'name': 'John Constantine'," +
//		        "    'phone': '+5213353319758'," +
//		        "    'email': 'hola@hola.com'" +
//		        "}," +
//		        "'charges': [{" +
//		        "    'payment_method': {" +
//		        "        'type': 'card'," +
//		        "        'token_id': 'tok_test_visa_4242'" +
//		        "    }, " +
//		        "    'amount': 35000" +
//		        "}]" +
//		        "}");
		Optional<Order> order = ordersConektaRepository.createOrder(new JSONObject(orderConektaRequest));
		//order.isPresent() validation
		return order.get();
	}
	
	@Override
	public Order createAnOrderWithAnOXXOPayCharge(OrderConektaRequest orderConektaRequest) {
		// TODO settings order for Oxxo Cash
		
//		JSONObject completeOrderJSON = new JSONObject("{" +
//		        "'currency': 'mxn'," +
//		        "'metadata': {" +
//		        "    'test': true"+
//		        "}," +
//		        "'line_items': [{" +
//		        "    'name': 'Box of Cohiba S1s'," +
//		        "    'description': 'Imported From Mex.'," +
//		        "    'unit_price': 35000," +
//		        "    'quantity': 1," +
//		        "    'tags': ['food', 'mexican food']," +
//		        "    'type': 'physical'" +
//		        "}]," +
//		        "'customer_info': { " +
//		        "    'name': 'John Constantine'," +
//		        "    'phone': '+5213353319758'," +
//		        "    'email': 'hola@hola.com'" +
//		        "}," +
//		        "'charges': [{" +
//		        "    'payment_method': {" +
//		        "        'type': 'oxxo_cash'" +
//		        "    }, " +
//		        "    'amount': 35000" +
//		        "}]" +
//		        "}");
		
		Optional<Order> order = ordersConektaRepository.createOrder(new JSONObject(orderConektaRequest));
		//order.isPresent() validation
		return order.get();
	}
	
	@Override
	public Order createAnOrderWithSPEIcharge(OrderConektaRequest orderConektaRequest) {
		// TODO settings order for SPEI charge
		
//		JSONObject speiOrderJSON = new JSONObject("{" +
//		        "'currency': 'mxn'," +
//		        "'metadata': {" +
//		        "    'test': true"+
//		        "}," +
//		        "'line_items': [{" +
//		        "    'name': 'Box of Cohiba S1s'," +
//		        "    'description': 'Imported From Mex.'," +
//		        "    'unit_price': 35000," +
//		        "    'quantity': 1," +
//		        "    'tags': ['food', 'mexican food']," +
//		        "    'type': 'physical'" +
//		        "}]," +
//		        "'customer_info': { " +
//		        "    'name': 'John Constantine'," +
//		        "    'phone': '+5213353319758'," +
//		        "    'email': 'hola@hola.com'" +
//		        "}," +
//		        "'charges': [{" +
//		        "    'payment_method': {" +
//		        "        'type': 'spei'" +
//		        "    }, " +
//		        "    'amount': 35000" +
//		        "}]" +
//		        "}");
		
		Optional<Order> order = ordersConektaRepository.createOrder(new JSONObject(orderConektaRequest));
		//order.isPresent() validation
		return order.get();
	}
	
	
	@Override
	public Order updateOrder(OrderConektaRequest orderConektaRequest) {
		/** ARGUMENTS: 
		 id: El id de la orden.
		 line_items: Lista de los productos que se venden en la orden. Debe tener al menos un producto.
		 shipping_lines: Lista de los costos de envío. Si la tienda en línea ofrece productos digitales, este parámetro es opcional.
		 tax_lines: Lista de los impuestos que se pagan.
		 discount_lines: Lista de los descuentos que se aplican a la orden.
		 pre_authorize: Indica si los cargos de la orden deben ser preautorizados.
		 customer_info: Hash que contiene la información del cliente.
		 shipping_contact: Detalles del envío, obligatorio en caso de mandar un shipping_line. 
		 					Si no recibimos un shipping_contact en la orden, se utilizará el shipping_contact 
		 						del customer por default.
		 charges: Lista de los cargos que se generaron para cubrir el monto de la orden.
		 */
		
//		JSONObject data = new JSONObject("{'currency':'MXN'}");
		
		Optional<Order> order = ordersConektaRepository.updateOrder(new JSONObject(orderConektaRequest));
		//order.isPresent() validation
		return order.get();
	}

	@Override
	public Order findOrder(String orderRequestId) {
		Optional<Order> order = ordersConektaRepository.findOrder(orderRequestId);
		//order.isPresent() validation
		return order.get();
	}
	
	/** 
	 * Capturar Orden
	 * Procesa una orden que ha sido previamente autorizada.
	 */
	@Override
	public void captureOrder(String orderRequestId) {
		ordersConektaRepository.captureOrder(orderRequestId);
	}

	
	/**
	 * Reembolsar Orden
	 * Una orden reembolsada describe los articulos, monto y razón por la que una orden está siendo reembolsada.
	 * 
	 * id:	El id de la orden.
	 * reason:	Razón del reembolso.
					requested_by_client
					cannot_be_fulfilled
					duplicated_transaction
					suspected_fraud
					other
	 * amount:	Monto a reembolsar en caso de reembolso parcial.
	 */
	@Override
	public Order refoundOrder(String orderRequestId) {
		
//		JSONObject validRefund = new JSONObject("{"
//				  + "'amount': 15000,"
//				  + "'reason': 'requested_by_client'"
//				+ "}");
		
		return ordersConektaRepository.refoundOrder(null).get();
	}
	
}
