package mx.ikii.payment.conekta.repository.customer;

import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import io.conekta.Conekta;
import io.conekta.Customer;
import io.conekta.Error;
import io.conekta.ErrorList;
import lombok.extern.slf4j.Slf4j;

/**
 * Clientes te permite crear suscripciones 
 * 	y guardar métodos de pago para usuarios que requieren pagos automáticos.
 */
@Slf4j
@Repository
public class CustomerConektaRepositoryImpl implements ICustomerConektaRepository {

	@Value("${conekta.api-key}")
	private String apiKey;

	@Value("${conekta.api-version}")
	private String apiVersion;

	@Override
	public Optional<Customer> createCustomer(JSONObject customerJSON) {
		Conekta.setApiKey(apiKey);
		
		Optional<Customer> customer = Optional.empty();
		try {
			customer = Optional.ofNullable(Customer.create(customerJSON));
		} catch (Error | ErrorList e) {
			log.info(e.getMessage());
			e.printStackTrace();
			// TODO: throw CustomError Commons
		}
		return customer;
	}

	@Override
	public Optional<Customer> updateCustomer(JSONObject customerJSON) {
		Customer customer = findCustomer(customerJSON.getString("id")).get();
		// TODO: throw CustomError if Order is empty
		try {
			 
			customer.update(customerJSON);
		} catch (Error | ErrorList e) {
			log.info(e.getMessage());
			e.printStackTrace();
			// TODO: throw CustomError Commons
		}
		return Optional.ofNullable(customer);
	}

	@Override
	public Optional<Customer> findCustomer(String customerId) {
		Optional<Customer> customer = Optional.empty();
		try {
			customer = Optional.ofNullable(Customer.find(customerId));
		} catch (Error | ErrorList e) {
			log.info(e.getMessage());
			e.printStackTrace();
			// TODO: throw CustomError Commons
		}
		return customer;
	}

	@Override
	public void deleteCustomer(String customerId) {
		Optional<Customer> customer = findCustomer(customerId);
		// TODO: throw CustomError Commons
		try {
			customer.get().delete();
		} catch (Error | ErrorList e) {
			log.info(e.getMessage());
			e.printStackTrace();
			// TODO: throw CustomError Commons
		}
	}

}
