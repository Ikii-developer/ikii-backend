package mx.ikii.payment.conekta.service;

import org.json.JSONException;
import org.json.JSONObject;

import io.conekta.Charge;
import io.conekta.Conekta;
import io.conekta.ConektaList;
import io.conekta.Customer;
import io.conekta.DiscountLine;
import io.conekta.Error;
import io.conekta.ErrorList;
import io.conekta.LineItems;
import io.conekta.Order;
import io.conekta.PaymentSource;
import io.conekta.ShippingContact;
import io.conekta.ShippingLine;
import io.conekta.TaxLine;
import lombok.extern.java.Log;

/** https://github.com/conekta/conekta-java
 * 
 * Notes on SSL cert
 * If you cannot connect to https://api.conekta.io, try installing the certificate in your java environment:
 * 	keytool -import -noprompt -trustcacerts -alias conekta -file %PROJECT_PATH%\ssl_data\ca_bundle.pem -keystore %JAVA_HOME%\lib\security\cacerts -storepass changeit
 * 
 * https://developers.conekta.com/api
 */
@Log
public class ConektaExamples {
	
	private final String apiKey = "key_eYvWV7gSDkNYXsmr";
	private final String apiVersion = "2.0.0";
	
	/**
	 * ########################################################################################################################
	 * ###########			CUSTOMERS					#######################################################################
	 * ########################################################################################################################
	 * 
	 * Clientes te permite crear suscripciones y guardar métodos de pago para usuarios que requieren pagos automáticos.
	 * 
	 * https://developers.conekta.com/api?language=java#customer
	 */
	public void createCustomer() throws Error, ErrorList {
		log.info("createCustomer:::::::::::: ");
		Conekta.setApiKey(apiKey);
		
		/**
		 * name: Nombre del cliente
		 * phone: Teléfono del cliente (formato internacional) (opcional).
		 * email: Correo electrónico del cliente.
		 * plan_id: id secundario de algún plan (opcional).
		 * 
		 * payment_sources: Arreglo de las tarjetas que ya se tokenizaron 
		 * 			o están por ser guardadas.
		 * 			Ve a la sección de métodos de pago para más detalles (opcional).
		 * 			https://developers.conekta.com/api?language=java#payment-source
		 * 
		 * shipping_contacts: Información de contacto el envío a cliente. 
		 * 		Ve a la sección de Contactos de envío para más detalles
		 * 		https://developers.conekta.com/api?language=java#shipping-contact
		 * 
		 * subscriptions: Subscripciones a las que puede estar asociado el cliente. 
		 * 		Ve a la sección de Suscripciones para más detalles (opcional).
		 * 		https://developers.conekta.com/api?language=java#subscription
		 *
		 */
		JSONObject customerJSON = new JSONObject("{"
		    + "'name': 'James Howlett', "
		    + "'email': 'james.howlett@forces.gov',"
		    + "'phone': '+5213353319758',"
		    + "'payment_sources': [{"
		        + "'token_id': 'tok_test_visa_4242',"
		        + "'type': 'card'"
		    + "}]"
		+ "}");

		Customer customer = Customer.create(customerJSON);
		System.out.println(customer);
	}
	
	/**
	 * Actualizar Cliente
	 */
	public void updateCustomer() throws JSONException, Error, ErrorList {
		Customer customer = Customer.find("cus_zzmjKsnM9oacyCwV3");
		customer.update(new JSONObject("{"
		  + "'name':'Mario Perez',"
		  + "'email':'usuario@example.com'"
		+ "}"));
		
		/** ARGUMENTs
		 * Id del cliente.
		 * Nombre del cliente.
		 * Teléfono del cliente (formato internacional) (opcional).
		 * Correo electrónico del cliente.
		 * plan_id: id secundario de algún plan (opcional).
		 * payment_sources: Arreglo de las tarjetas que ya se tokenizaron o están por ser guardadas. 
		 * 					Ve a la sección de métodos de pago para más detalles (opcional).
		 * 				  	https://developers.conekta.com/api?language=java#payment-source
		 * shipping_contacts: Información de contacto el envío a cliente. 
		 * 						Ve a la sección de Contactos de envío para más detalles (opcional).
		 * 						https://developers.conekta.com/api?language=java#shipping-contact
		 * subscriptions: Subscripciones a las que puede estar asociado el cliente. 
		 * 					Ve a la sección de Suscripciones para más detalles (opcional).
		 */
	}
	
	/** 
	 * Eliminar Cliente
	 */
	public void deleteCustomer() throws Error, ErrorList {
		Customer customer = Customer.find("cus_zzmjKsnM9oacyCwV3");
		customer.delete();
	}
	
	/**
	 * ========================================================================================================================
	 * =========			ORDERS						=======================================================================
	 * ========================================================================================================================
	 * Una orden representa una compra. Incluye todos los detalles relacionados a ella, 
	 * 	como método de pago, envío, productos comprados, cargos, descuentos e impuestos.
	 * 
	 * https://developers.conekta.com/api?language=java#order
	 */
	public void createAnOrderWithCardCharge() throws ErrorList, Error {
		
		JSONObject completeOrderJSON = new JSONObject("{" +
		        "'currency': 'mxn'," +
		        "'metadata': {" +
		        "    'test': true"+
		        "}," +
		        "'line_items': [{" +
		        "    'name': 'Box of Cohiba S1s'," +
		        "    'description': 'Imported From Mex.'," +
		        "    'unit_price': 35000," +
		        "    'quantity': 1," +
		        "    'tags': ['food', 'mexican food']," +
		        "    'type': 'physical'" +
		        "}]," +
		        "'customer_info': { " +
		        "    'name': 'John Constantine'," +
		        "    'phone': '+5213353319758'," +
		        "    'email': 'hola@hola.com'" +
		        "}," +
		        "'charges': [{" +
		        "    'payment_method': {" +
		        "        'type': 'card'," +
		        "        'token_id': 'tok_test_visa_4242'" +
		        "    }, " +
		        "    'amount': 35000" +
		        "}]" +
		        "}");

		Order completeOrder = Order.create(completeOrderJSON);

		System.out.println(completeOrder.charges.get(0));
		
	}
	
	public void createAnOrderWithAnOXXOPayCharge() throws ErrorList, Error {
		Conekta.setApiKey(apiKey);
		Conekta.apiVersion = apiVersion;
				
		JSONObject completeOrderJSON = new JSONObject("{" +
		        "'currency': 'mxn'," +
		        "'metadata': {" +
		        "    'test': true"+
		        "}," +
		        "'line_items': [{" +
		        "    'name': 'Box of Cohiba S1s'," +
		        "    'description': 'Imported From Mex.'," +
		        "    'unit_price': 35000," +
		        "    'quantity': 1," +
		        "    'tags': ['food', 'mexican food']," +
		        "    'type': 'physical'" +
		        "}]," +
		        "'customer_info': { " +
		        "    'name': 'John Constantine'," +
		        "    'phone': '+5213353319758'," +
		        "    'email': 'hola@hola.com'" +
		        "}," +
		        "'charges': [{" +
		        "    'payment_method': {" +
		        "        'type': 'oxxo_cash'" +
		        "    }, " +
		        "    'amount': 35000" +
		        "}]" +
		        "}");

		Order oxxoOrder = Order.create(completeOrderJSON);

		ConektaList conektaList = oxxoOrder.charges;
		
		conektaList.forEach(c->{
			System.out.println(c);
		});

		//OxxoPayment oxxoPayment = (OxxoPayment) payment_method;
		
	}
	
	public void createAnOrderWithSPEIcharge() throws ErrorList, Error {
		Conekta.setApiKey("key_eYvWV7gSDkNYXsmr");
		Conekta.apiVersion = apiVersion;
		
		JSONObject speiOrderJSON = new JSONObject("{" +
		        "'currency': 'mxn'," +
		        "'metadata': {" +
		        "    'test': true"+
		        "}," +
		        "'line_items': [{" +
		        "    'name': 'Box of Cohiba S1s'," +
		        "    'description': 'Imported From Mex.'," +
		        "    'unit_price': 35000," +
		        "    'quantity': 1," +
		        "    'tags': ['food', 'mexican food']," +
		        "    'type': 'physical'" +
		        "}]," +
		        "'customer_info': { " +
		        "    'name': 'John Constantine'," +
		        "    'phone': '+5213353319758'," +
		        "    'email': 'hola@hola.com'" +
		        "}," +
		        "'charges': [{" +
		        "    'payment_method': {" +
		        "        'type': 'spei'" +
		        "    }, " +
		        "    'amount': 35000" +
		        "}]" +
		        "}");

		Order speiOrder = Order.create(speiOrderJSON);
		Object charge = speiOrder.charges.get(0);
		System.out.println(charge);
	}
	
	/**
	 * Actualiza una Orden existente.
	 */
	public void updateOrder() throws ErrorList, Error {
		JSONObject data = new JSONObject("{'currency':'MXN'}");
		Order order = Order.find("ord_2fw8EWJusiRrxdPzT");
		order.update(data);
		
		/* ARGUMENTS: 
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
	}
	
	/** Capturar Orden
	 * Procesa una orden que ha sido previamente autorizada.
	 */
	public void getOrder() throws ErrorList, Error {
		Order order = Order.find("ord_2fw8EWJusiRrxdPzT");
		order.capture();
	}
	
	/** Reembolsar Orden
	 * Una orden reembolsada describe los articulos, monto y razón por la que una orden está siendo reembolsada.
	 */
	public void refoundOrder() throws Exception {
		Order order = Order.find("ord_2fw8EWJusiRrxdPzT");
		JSONObject validRefund = new JSONObject("{"
		  + "'amount': 15000,"
		  + "'reason': 'requested_by_client'"
		+ "}");
		order.refund(validRefund);
		
		/**
		 * id:	El id de la orden.
		 * reason:	Razón del reembolso.
						requested_by_client
						cannot_be_fulfilled
						duplicated_transaction
						suspected_fraud
						other
		 * amount:	Monto a reembolsar en caso de reembolso parcial.
		 */
	}
	
	/**
	 * ************************************************************************************************************************
	 * ************			PAYMENT METHODS				***********************************************************************
	 * ************************************************************************************************************************
	 * El objeto Payment Source describe el método de pago. 
	 * 		Este pago puede ser online (pagos con tarjeta) o offline (OxxoPay). 
	 * 		Recuerda que tendras que agregar un webhook listener para los pagos offline.
	 * 
	 * type: Tipo del método de pago. Por el momento los único tipos permitido son "card" y "oxxo_recurrent".
	 * token_id: Id de token que será utilizado para crear un método de pago de tipo "card". 
	 * 		Ve el tutorial de suscripciones para más información sobre cómo tokenizar tarjetas.
	 * 		https://developers.conekta.com/api?language=java#payment-source
	 */
	
	public void createPaymentMethod() throws Error, ErrorList, JSONException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		Customer customer = Customer.find("cus_zzmjKsnM9oacyCwV3");
		JSONObject paymentSourceJSON = new JSONObject("{"
		  + "'token_id': 'tok_test_visa_4242',"
		  + "'type': 'card'"
		+ "}");

		PaymentSource paymentSource = customer.createPaymentSource(paymentSourceJSON);
	}
	
	/**
	 * Actualizar Método de Pago
	 * 
	 * id: Id del método de pago.
	 * name: Nombre del tarjeta habiente.
	 * exp_month: Mes de expiración de la tarjeta.
	 * exp_year: Año de expiración de la tarjeta.
	 * address: Dirección del tarjethabiente.
	 */
	public void updatePaymentMethod() throws Error, ErrorList {
		Customer customer = Customer.find("cus_zzmjKsnM9oacyCwV3");
		JSONObject paymentSourceUpdateJSON = new JSONObject("{'exp_month': '11'}");
		PaymentSource paymentSourceUpdated = (PaymentSource) customer.payment_sources.get(0);

		paymentSourceUpdated.update(paymentSourceUpdateJSON);
	}
	
	/**
	 * Eliminar Método de Pago
	 */
	public void deletePaymentMethod() throws Error, ErrorList {
		Customer customer = Customer.find("cus_zzmjKsnM9oacyCwV3");
		PaymentSource paymentSourceDeleted = (PaymentSource) customer.payment_sources.get(0);

		paymentSourceDeleted.delete();
	}
	
	/**
	 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 * ++++++++++			CHARGUES						+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 * El objeto Cargo contiene toda la información relacionada al pago de un orden. Este objeto pertenece al objeto orden.
	 * 
	 * https://developers.conekta.com/api?language=java#charge
	 */
	public void createCharge() throws ErrorList, Error, JSONException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		JSONObject data = new JSONObject("{"
		  + "'payment_method': {"
		    + "'type': 'oxxo_cash',"
		    + "'expires_at': 1594401352"
		  + "}"
		+"}");

		Order order = Order.find("ord_2fw8EWJusiRrxdPzT");
		Charge charge = order.createCharge(data);
		/** ARGUMENTS
		 * amount:	Cantidad a pagar en centavos.
		 * payment_method: Método de pago utilizado en el cargo. Ve a la sección de métodos de pago para más detalles
		 * expires_at: Fecha de expiración del método.
		 * token_id: Token de la tarjeta. Obligatorio para el tipo `card`.
		 */
	}
	
	
	/**
	 * °°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°
	 * °°°°°°°°°°°			LINE ITEMS						°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°
	 * °°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°
	 * 
	 * Este objeto representa los productos de la Orden.
	 * Este objeto pertenece al objeto Orden.
	 * 
	 * https://developers.conekta.com/api?language=java#line-item
	 */
	public void createLineTime() throws JSONException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, Error, ErrorList {
		Order order = Order.find("ord_2fw8EWJusiRrxdPzT");
		LineItems lineItem = order.createLineItem(new JSONObject("{"
		  + "'name': 'Box of Cohiba S1s',"
		  + "'unit_price': 35000,"
		  + "'quantity': 1"
		+ "}"));
		/** ARGUMENTOS
		 * name: Nombre del producto.
		 * description: Pequeña descripción del producto (< 250). (opcional)
		 * unit_price: El precio por unidad expresado en centavos.
		 * quantity: Cantidad vendida del producto.
		 * sku: Indica el Storage Keeping Unit designado por la empresa
		 * tags: Arreglo que contiene las categorías a las que pertenece el producto. Debe tener al menos un elemento. (opcional)
		 * brand: Marca del producto. (opcional)
		 * metadata: Hash en donde el usuario puede enviar información adicional por cada producto. (opcional)
		 */
	}
	
	public void updateLineItems() throws ErrorList, Error {
		Order order = Order.find("ord_2fw8EWJusiRrxdPzT");
		LineItems lineItem = (LineItems) order.line_items.get(0);
		lineItem.update(new JSONObject("{"
		  + "'unit_price': 21000,"
		  + "'quantity': 2"
		+ "}"));
		/**
		 * name: Nombre del producto.
		 * description: Pequeña descripción del producto (< 250). (opcional)
		 * unit_price: El precio por unidad expresado en centavos.
		 * quantity: Cantidad vendida del producto
		 * sku: Indica el Storage Keeping Unit designado por la empresa
		 * tags: Arreglo que contiene las categorías a las que pertenece el producto. Debe tener al menos un elemento. (opcional)
		 * brand: Marca del producto. (opcional)
		 * metadata: Hash en donde el usuario puede enviar información adicional por cada producto. (opcional)
		 */
	}
	
	/** Eliminar Producto	 */
	public void deleteLineItems() throws ErrorList, Error {
		Order order = Order.find("ord_2fw8EWJusiRrxdPzT");
		LineItems lineItem = (LineItems) order.line_items.get(0);
		lineItem.delete();
	}
	
	/**
	 * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
	 * |||||||||||			SHIPPING LINES					|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
	 * ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
	 * 
	 * Un Envío describe los detalles del método de envío de una orden, tales como el medio, el costo, el proveedor y el número de guía.
	 * 
	 * https://developers.conekta.com/api?language=java#shipping-line
	 */
	public void createShipingLines() throws ErrorList, Error, JSONException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		Order order = Order.find("ord_2fw8EWJusiRrxdPzT");
		JSONObject shippingLineParams = new JSONObject("{"
		  + "'description': 'Free Shipping',"
		  + "'amount': 0,"
		  + "'tracking_number': 'TRACK123',"
		  + "'carrier': 'USPS',"
		  + "'method': 'Train'"
		+ "}");

		order.createShippingLine(shippingLineParams);
		/**
		 * amount: El precio del envío en centavos.
		 * tracking_number: Número de rastreo que el proveedor de envios proporciona. (opcional)
		 * carrier: Nombre del proveedor de envío.
		 * method: Método de envío. (opcional)
		 * hash: metadata Hash en donde el usuario puede enviar información adicional por cada `shipping_line`. (opcional)
		 */
	}
	
	public void updateShipLines() throws JSONException, Error, ErrorList {
		Order order = Order.find("ord_2fw8EWJusiRrxdPzT");
		ShippingLine shippingLine = (ShippingLine) order.shipping_lines.get(0);
		shippingLine.update(new JSONObject("{"
		    + "'tracking_number': 'TRACK124'"
		    + "}")
		);
		
		/** ARGUMENTS
		 * id: El id del envío.
		 * amount: El precio del envío en centavos.
		 * tracking_number: Número de rastreo que el proveedor de envios proporciona. (opcional)
		 * carrier: Nombre del proveedor de envío.
		 * method: Método de envío. (opcional)
		 * metadata: Hash en donde el usuario puede enviar información adicional por cada shipping_line. (opcional)-
		 */
	}
	
	public void deleteShipLines() throws ErrorList, Error {
		Order order = Order.find("ord_2fw8EWJusiRrxdPzT");
		ShippingLine shippingLine = (ShippingLine) order.shipping_lines.get(0);
		shippingLine.delete();
	}
	
	
	/**
	 * ########################################################################################################################
	 * #########			SHIPPING CONTACTS				###################################################################
	 * ########################################################################################################################
	 * 
	 * Información de contacto para el envío al cliente.
	 * 
	 * https://developers.conekta.com/api?language=java#shipping-contact
	 */
	
	/*
	 * Crear Contacto de Envío (Cliente)
	 * Crea un nuevo Contacto de Envío para un cliente existente.
	 */
	public void createShippingContactClient() throws JSONException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, Error, ErrorList {
		Customer customer = Customer.find("cus_zzmjKsnM9oacyCwV3");
		customer.createShippingContact(new JSONObject("{"
		  + "'receiver': 'Mario perez',"
		  + "'phone': '+5215555555555',"
		  + "'between_streets': 'Morelos y Campeche',"
		  + "'address': {"
		    + "'street1': 'Nuevo Leon 4',"
		    + "'city': 'Ciudad de Mexico',"
		    + "'state': 'Ciudad de Mexico',"
		    + "'postal_code': '78215',"
		    + "'country': 'MX'"
		  + "}"
		+ "}"));
		
		/** ARGUMENTOS
		 * Phone: Teléfono de contacto.
		 * receiver: Nombre de la persona a la que va dirigido el paquete. (opcional)
		 * between_streets: Las calles al lado de la dirección de entrega. (opcional)
		 * address: Dirección de entrega.
		 * address.street1: La primera línea para la dirección de envío. Usualmente es utilizado para calle y número.
		 * address.street2: La segunda línea para la dirección de envío. Usualmente es utilizado para número interior, suite, fraccionamiento o delegación (opcional).
		 * address.city: La ciudad de la dirección de envío.
		 * address.state: El estado de la dirección de envío.
		 * address.country: País de destino, utiliza el formato ISO 3166-1 de 2 dígitos.
		 * address.postal_code: El código postal de la dirección de envío.
		 * address.residential: Booleano que indica si es una dirección de envío residencial. Por defecto se toma como verdadero (opcional). 
		 */
	}
	public void updateShippingContactClient() throws Error, ErrorList {
		Customer customer = Customer.find("cus_zzmjKsnM9oacyCwV3");
		ShippingContact shippingContact = null;
		shippingContact =
		  (ShippingContact) customer.shipping_contacts.get(0);
		shippingContact.update(
		  new JSONObject("{ 'receiver': 'Eduardo Enriquez' }")
		);
	}
	
	public void deleteShipingContactClient() throws Error, ErrorList {
		Customer customer = Customer.find("cus_zzmjKsnM9oacyCwV3");
		ShippingContact shippingContact = null;
		shippingContact =
		  (ShippingContact) customer.shipping_contacts.get(0);
		shippingContact.delete();
	}
	
	/*
	 * Crear Contacto de Envío (Orden)
	 * Crea un nuevo Contacto de Envío para una orden existente.
	 */
	public void createShippingContactOrder() throws JSONException, Error, ErrorList {
		Order order = Order.find("ord_2fw8EWJusiRrxdPzT");
		order.createShippingContact(new JSONObject("{"
		  + "'receiver': 'Mario perez',"
		  + "'phone': '+5215555555555',"
		  + "'between_streets': 'Campeche y Morelos',"
		  + "'address': {"
		    + "'street1': 'Nuevo Leon 4',"
		    + "'city': 'Ciudad de Mexico',"
		    + "'state': 'Ciudad de Mexico',"
		    + "'country': 'MX',"
		    + "'postal_code': '78215'"
		  + "}"
		+ "}"));
		/** ARGUMENTOS
		 * Phone: Teléfono de contacto.
		 * receiver: Nombre de la persona a la que va dirigido el paquete. (opcional)
		 * between_streets: Las calles al lado de la dirección de entrega. (opcional)
		 * address: Dirección de entrega.
		 * address.street1: La primera línea para la dirección de envío. Usualmente es utilizado para calle y número.
		 * address.street2: La segunda línea para la dirección de envío. Usualmente es utilizado para número interior, suite, fraccionamiento o delegación (opcional).
		 * address.city: La ciudad de la dirección de envío.
		 * address.state: El estado de la dirección de envío.
		 * address.country: País de destino, utiliza el formato ISO 3166-1 de 2 dígitos.
		 * address.postal_code: El código postal de la dirección de envío.
		 * address.residential: Booleano que indica si es una dirección de envío residencial. Por defecto se toma como verdadero (opcional). 
		 */
	}
	
	
	
	/**
	 * ========================================================================================================================
	 * ==========			DISCOUNT LINES					===================================================================
	 * ========================================================================================================================
	 * 
	 * Describe los descuentos que se aplicarán a una orden.
	 * 
	 * https://developers.conekta.com/api?language=java#discount-line
	 */
	public void createDiscount() throws JSONException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, Error, ErrorList {
		Order order = Order.find("ord_2fw8EWJusiRrxdPzT");
		order.createDiscountLine(new JSONObject("{"
		  + "'code': 'Cupon de descuento',"
		  + "'amount': 5,"
		  + "'type': 'loyalty'"
		+ "}"));
		/** ARGUMENTOS
		 * code: Código del descuento.
		 * type: Puede ser 'loyalty', 'campaign', 'coupon' o 'sign'
		 * amount: La cantidad a descontar de la suma total de todos los pagos, en centavos.
		 */
	}
	
	public void updateDiscount() throws ErrorList, Error {
		Order order = Order.find("ord_2fw8EWJusiRrxdPzT");
		TaxLine taxLine = null;
		taxLine = (TaxLine) order.tax_lines.get(0);
		taxLine.update(
		  new JSONObject("{'description': 'IETU'}")
		);
	}
	
	public void deleteDiscount() throws ErrorList, Error {
		Order order = Order.find("ord_2fw8EWJusiRrxdPzT");
		DiscountLine discountLine = null;
		discountLine = (DiscountLine) order.discount_lines.get(0);
		discountLine.delete();
	}
	
	
	/**
	 * ************************************************************************************************************************
	 * ************			TAX LINES						*******************************************************************
	 * ************************************************************************************************************************
	 * 
	 * Describe los impuestos que se aplicarán a la orden.
	 * 
	 * https://developers.conekta.com/api?language=java#tax-line
	 */
	
	public void createTaxLine() throws JSONException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, Error, ErrorList {
		Order order = Order.find("ord_2fw8EWJusiRrxdPzT");
		order.createTaxLine(new JSONObject("{"
		  + "'description': 'IVA',"
		  + "'amount': 100"
		+ "}"));
		/** ARGUMENTS
		 * description: Código del impuesto.
		 * amount: La cantidad a cobrar por impuesto en centavos.
		 * metadata: Hash en donde el usuario puede enviar información adicional por cada tax_line (opcional). 
		 */
	}

	public void updateTaxLine() throws ErrorList, Error {
		Order order = Order.find("ord_2fw8EWJusiRrxdPzT");
		TaxLine taxLine = null;
		taxLine = (TaxLine) order.tax_lines.get(0);
		taxLine.update(
		  new JSONObject("{'description': 'IETU'}")
		);
	}
	
	public void deleteTaxLine() throws ErrorList, Error {
		Order order = Order.find("ord_2fw8EWJusiRrxdPzT");
		TaxLine taxLine = null;
		taxLine = (TaxLine) order.tax_lines.get(0);
		taxLine.delete();
	}
	
	
	
	/**
	 * ############################################################
	 * ###########			PLANES						###########
	 * ############################################################
	 * Planes son plantillas que te permiten crear suscripciones. 
	 * Dentro del plan definirás la cantidad y frecuencia con el cual generarás cobros recurrentes a tus clientes.
	 * 
	 * https://developers.conekta.com/api?language=java#plan
	 */
	
	/**
	 * ############################################################
	 * ###########			Suscripciones				###########
	 * ############################################################
	 * Suscripciones son una manera de realizar cargos a un cliente con una cantidad fija de manera recurrente. 
	 * Puedes cambiar el plan, pausar, cancelar y reanudar una suscripción a tu gusto.
	 * 
	 * https://developers.conekta.com/api?language=java#subscription
	 */
	
	/**
	 * ############################################################
	 * ###########			Link de Pago				###########
	 * ############################################################
	 * Un objeto checkout representa un posible pago que se efectuaría a través del checkout de conekta. 
	 * Este puede ser de dos tipos: PaymentLink y PaymentButton. 
	 * Incluye todos los detalles relacionados a ella, 
	 * 	como método de pago habilitados, productos a comprar, información del customer.
	 * 
	 * https://developers.conekta.com/api?language=java#checkout
	 */	
	
	/**
	 * ############################################################
	 * ###########			Paginación					###########
	 * ############################################################
	 * Una Lista representa una colección de objetos en un formato que nos permite paginar de una manera eficiente.
	 * 
	 * https://developers.conekta.com/api?language=java#pagination
	 */	
	
	/**
	 * ############################################################
	 * ###########			Eventos						###########
	 * ############################################################
	 * Eventos son la manera en que te podemos comunicar cualquier cosa que ha pasado en tu cuenta. 
	 * Para poderte notificar, creamos un objeto de evento, el cual contiene toda la información que necesitas saber sobre el evento. 
	 * Un ejemplo de eventos es cuando el pago de un cargo es exitoso creamos el evento charge.paid.
	 * 
	 * Por medio de nuestro API podrás realizar llamadas para poder obtener información sobre un evento en específico.
	 * Otra manera de poder recibir estos eventos es por medio de webhooks, donde mandaremos cada uno de los eventos directamente a tu servidor. 
	 * Para saber un poco más sobre webhooks, puedes ver la sección de webhooks.
	 * 
	 * https://developers.conekta.com/api?language=java#events
	 */	
	
	
	/**
	 * ############################################################
	 * ###########			ERRORS						###########
	 * ############################################################
	 * Por medio de nuestro API, podrás ser notificado con toda la información en caso de cualquier error al momento de crear cualquier llamada a nuestro servicio.
	 * 
	 * https://developers.conekta.com/api?language=java#errors
	 */	
	
	public void handleErrors() throws Error {
		JSONObject incompleteOrderJSON = new JSONObject("{" +
		        "'currency': 'mxn'," +
		        "'metadata': {" +
		        "    'test': true"+
		        "}," +
		        "'line_items': [{" +
		        "    'name': 'Box of Cohiba S1s'," +
		        "    'description': 'Imported From Mex.'," +
		        "    'unit_price': 35000," +
		        "    'quantity': 1," +
		        "    'tags': ['food', 'mexican food']," +
		        "    'type': 'physical'" +
		        "}]," +
		        "'customer_info': { " +
		        "    'name': 'John Constantine'," +
		        "    'phone': '+5213353319758'," +
		        "    'email': 'hola@hola.com'" +
		        "}," +
		        "'charges': [{" +
		        "    'amount': 35000" +
		        "}]" +
		        "}");

		try {
		   Order.create(incompleteOrderJSON);
		} catch (ErrorList e) {
		   System.out.println(e.details.get(0).message);
		}
		/**
		 * type: Contiene el tipo de error y el codigo del error.
		 * log_id: El id del log de la petición http registrando este error.
		 * data: El objeto modificado por esta petición, en caso de `processing_error` por ejemplo, los cargos todavía se crean.
		 * details: Lista detallada de los errores.
		 * 		message: Mensaje legible para humanos el cual provee más detalles sobre el error. 
		 * 				Este mensaje debe ser desplegado al usuario y está disponible en inglés y español. 
		 * 				Para cargos de tarjeta, el mensaje puede ser mostrado al usuario.
		 * 		debug_message: Mensaje legible para humanos el cual provee más detalles sobre el error. 
		 * 						Este mensaje debe ser usado internamente para depuración y solo está disponible en inglés. 
		 * 						Para cargos de tarjeta, el mensaje puede ser mostrado al usuario.
		 * 		code: Un código corto y específico detallando processing_error.
		 * 		params: El parámetro al cual este error está relacionado. Puedes usar este error para subrayar campos de texto erróneos.
		 */
	}

}
