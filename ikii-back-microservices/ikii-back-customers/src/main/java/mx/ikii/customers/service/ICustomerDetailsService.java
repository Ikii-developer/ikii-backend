package mx.ikii.customers.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import mx.ikii.commons.persistence.collection.CustomerDetails;

public interface ICustomerDetailsService {

	CustomerDetails getById(String customerDetailsId);
	
	CustomerDetails getByCustomerId(String customerId);
	
	List<CustomerDetails> getAll(Pageable pageable);
	
	CustomerDetails create(CustomerDetails customerDetails);
	
	CustomerDetails update(CustomerDetails customerDetails, String id);
	
	void delete(String customerDetailsId);
	
}
