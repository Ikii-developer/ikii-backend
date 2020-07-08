package mx.ikii.customers.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.ikii.commons.payload.request.customer.CustomerAdressRequest;
import mx.ikii.commons.payload.response.customer.CustomerAdressResponse;

/**
 * This interface contains the methods related to CRUD operations to Customer address
 */
public interface ICustomerAdressServiceWrapper {

	CustomerAdressResponse getById(String customerAddressId);
	
	Page<CustomerAdressResponse> getByCustomerId(String customerId, Pageable pageable);
	
	CustomerAdressResponse create(CustomerAdressRequest customerAddressRequest);
	
	CustomerAdressResponse update(CustomerAdressRequest customerAddressRequest, String id);
	
	void delete(String customerAddressId);
	
}
