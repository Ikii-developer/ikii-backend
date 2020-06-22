package mx.ikii.payment.conekta.service.customer;

import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.conekta.Customer;
import lombok.extern.slf4j.Slf4j;
import mx.ikii.payment.conekta.repository.customer.ICustomerConektaRepository;
import mx.ikii.payment.payload.request.CustomerConektaRequest;

/**
 * ########################################################################
 * ###########			CUSTOMERS					#######################
 * ########################################################################
 * 
 * Clientes te permite crear suscripciones y guardar métodos de pago 
 * 	para usuarios que requieren pagos automáticos.
 * 
 * https://developers.conekta.com/api?language=java#customer
 */
@Slf4j
@Service
public class CustomerConektaServiceImpl implements ICustomerConektaService {

	@Autowired
	private ICustomerConektaRepository customerConektaRepository;
	
	/**
	 * <pre>
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
	 * </pre>
	 */
	@Override
	public Customer createCurstomer(CustomerConektaRequest customerConektaRequest) {
		log.info("createCurstomer: " + customerConektaRequest);

//		Customer.create( new JSONObject("{"
//				  + "'name':  'Mario Perez', "
//				  + "'email': 'usuario@example.com',"
//				  + "'phone': '+5215555555555',"
//				  + "'payment_sources': [{"
//				      + "'token_id': 'tok_test_visa_4242',"
//				      + "'type': 'card'"
//				  + "}],"
//				  + "'shipping_contacts': [{"
//				      + "'phone': '+5215555555555',"
//				      + "'receiver': 'Marvin Fuller',"
//				      + "'address': {"
//				          + "'street1': 'Nuevo Leon 4',"
//				          + "'country': 'CA',"
//				          + "'postal_code': '06100'"
//				      + "}"
//				  + "}]"
//				  +"}"));

		Optional<Customer> customer = customerConektaRepository.createCustomer(new JSONObject(customerConektaRequest));
		//customer.isPresent() validation
		return customer.get();
	}

	/**
	 * <pre>
	 * ARGUMENTS
	 * Id del cliente.
	 * Nombre del cliente.
	 * Teléfono del cliente (formato internacional) (opcional).
	 * Correo electrónico del cliente.
	 * plan_id: id secundario de algún plan (opcional).
	 * 
	 * payment_sources: Arreglo de las tarjetas que ya se tokenizaron o están por ser guardadas. 
	 * 					Ve a la sección de métodos de pago para más detalles (opcional).
	 * 				  	https://developers.conekta.com/api?language=java#payment-source
	 * 
	 * shipping_contacts: Información de contacto el envío a cliente. 
	 * 						Ve a la sección de Contactos de envío para más detalles (opcional).
	 * 						https://developers.conekta.com/api?language=java#shipping-contact
	 * 
	 * subscriptions: Subscripciones a las que puede estar asociado el cliente. 
	 * 					Ve a la sección de Suscripciones para más detalles (opcional).
	 * </pre>
	 */
	@Override
	public Customer updateCurstomer(CustomerConektaRequest customerConektaRequest) {
		
//		new JSONObject("{"
//				  + "'name':'Mario Perez',"
//				  + "'email':'usuario@example.com'"
//				+ "}");
		
		Optional<Customer> customer = customerConektaRepository.updateCustomer(new JSONObject(customerConektaRequest));
		//customer.isPresent() validation
		return customer.get();
	}

	@Override
	public Customer findCurstomer(String customerId) {
		log.info("findCurstomer: " + customerId);
		Optional<Customer> customer = customerConektaRepository.findCustomer(customerId);
		//customer.isPresent() validation
		return customer.get();
	}

	@Override
	public void deleteCurstomer(String customerId) {
		log.info("deleteCurstomer: " + customerId);
		customerConektaRepository.deleteCustomer(customerId);
	}

}
