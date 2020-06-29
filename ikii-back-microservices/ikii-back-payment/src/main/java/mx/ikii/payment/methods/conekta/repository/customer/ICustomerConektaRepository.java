package mx.ikii.payment.methods.conekta.repository.customer;

import org.json.JSONObject;

import io.conekta.Customer;

public interface ICustomerConektaRepository {
	
	Customer findCustomer(String customerId);
	
	Customer createCustomer(JSONObject customerJSON);

	Customer updateCustomer(JSONObject customerJSON);

	void deleteCustomer(String customerId);

}
