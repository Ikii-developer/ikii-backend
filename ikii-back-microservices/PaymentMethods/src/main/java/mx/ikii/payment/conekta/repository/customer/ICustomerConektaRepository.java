package mx.ikii.payment.conekta.repository.customer;

import java.util.Optional;

import org.json.JSONObject;

import io.conekta.Customer;

public interface ICustomerConektaRepository {

	Optional<Customer> createCustomer(JSONObject customerJSON);

	Optional<Customer> updateCustomer(JSONObject customerJSON);

	Optional<Customer> findCustomer(String customerId);

	void deleteCustomer(String customerId);

}
