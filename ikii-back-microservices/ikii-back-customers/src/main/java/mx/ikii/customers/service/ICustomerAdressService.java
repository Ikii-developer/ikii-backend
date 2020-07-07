package mx.ikii.customers.service;

import java.util.List;

import mx.ikii.commons.persistence.collection.CustomerAdress;

public interface ICustomerAdressService {

	CustomerAdress getById(String customerAdressId);
	
	List<CustomerAdress> getByCustomerId(String customerId);
	
	CustomerAdress createCustomerAddress(CustomerAdress request);
	
	void deleteCustomerAddress(String customerAdressId);
	
	CustomerAdress updateCustomerAddress(CustomerAdress request, String id);
}
