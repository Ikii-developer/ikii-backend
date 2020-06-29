package mx.ikii.payment.conekta.service.customer;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.conekta.Customer;
import lombok.extern.slf4j.Slf4j;
import mx.ikii.payment.conekta.repository.customer.ICustomerConektaRepository;
import mx.ikii.payment.payload.request.CustomerConektaRequest;

/**
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
	
	@Override
	public Customer createCurstomer(CustomerConektaRequest customerConektaRequest) {
		log.info("createCurstomer: " + customerConektaRequest);
		Customer customer = customerConektaRepository.createCustomer(new JSONObject(customerConektaRequest));
		//Okta specific business logic
		return customer;
	}

	@Override
	public Customer updateCurstomer(CustomerConektaRequest customerConektaRequest) {
		log.info("updateCurstomer: " + customerConektaRequest);
		Customer customer = customerConektaRepository.updateCustomer(new JSONObject(customerConektaRequest));
		//Okta specific business logic
		return customer;
	}

	@Override
	public Customer findCurstomer(String customerId) {
		log.info("findCurstomer: " + customerId);
		Customer customer = customerConektaRepository.findCustomer(customerId);
		//Okta specific business logic
		return customer;
	}

}
