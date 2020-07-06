package mx.ikii.customers.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import mx.ikii.commons.payload.request.customer.CustomerDetailsRequest;
import mx.ikii.commons.payload.response.customer.CustomerDetailsResponse;

public interface ICustomerDetailsController {

	@GetMapping("/customer-details/{id}")
	ResponseEntity<CustomerDetailsResponse> getById(String id);
	
	@GetMapping("/customer-details/{id}")
	ResponseEntity<CustomerDetailsResponse> getByCustomerId(String customerId);
	
	@GetMapping("/customer-details")
	ResponseEntity<Page<CustomerDetailsResponse>> getAll(Pageable pageable);
	
	@PostMapping("/customer-details")
	ResponseEntity<CustomerDetailsResponse> create(CustomerDetailsRequest customerDetails);
	
	@PutMapping("/customer-details/{id}")
	ResponseEntity<CustomerDetailsResponse> update(CustomerDetailsRequest customerDetails, String id);
	
	@DeleteMapping("/customer-details/{id}")
	ResponseEntity<Void> delete(String id);

}
