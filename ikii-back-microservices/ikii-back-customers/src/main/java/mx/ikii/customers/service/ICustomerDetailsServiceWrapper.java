package mx.ikii.customers.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.ikii.commons.payload.request.customer.CustomerDetailsRequest;
import mx.ikii.commons.payload.response.customer.CustomerDetailsResponse;

public interface ICustomerDetailsServiceWrapper {

	CustomerDetailsResponse getById(String id);
	
	CustomerDetailsResponse getByCustomerId(String customerId);
	
	Page<CustomerDetailsResponse> getAll(Pageable pageable);
	
	CustomerDetailsResponse create(CustomerDetailsRequest customerDetails);
	
	CustomerDetailsResponse update(CustomerDetailsRequest customerDetails, String id);
	
	void delete(String id);
	
}
