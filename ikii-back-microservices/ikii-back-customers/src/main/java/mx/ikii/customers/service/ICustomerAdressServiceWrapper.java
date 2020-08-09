package mx.ikii.customers.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.ikii.commons.payload.request.customer.CustomerAdressRequest;
import mx.ikii.commons.payload.response.customer.CustomerAdressResponse;

/**
 * This interface contains the methods related to CRUD operations to Customer address
 */
public interface ICustomerAdressServiceWrapper {

	Page<CustomerAdressResponse> getAll(Pageable pageable);
	
	CustomerAdressResponse getById(String customerAddressId);
	
	Page<CustomerAdressResponse> getByCustomerId(String customerId, Pageable pageable);
	
	CustomerAdressResponse create(CustomerAdressRequest customerAddressRequest);
	
	CustomerAdressResponse update(CustomerAdressRequest customerAddressRequest, String id);
	
	void delete(String customerAddressId);
	
	List<CustomerAdressResponse> nearByMe(Double latitude, Double longitude, Double distance);
	
}
