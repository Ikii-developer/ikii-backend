package mx.ikii.customers.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.ikii.commons.payload.request.customer.CustomerDetailsRequest;
import mx.ikii.commons.payload.response.customer.CustomerDetailsResponse;

@RestController
@RequestMapping("/customer-details")
public interface ICustomerDetailsController {

	@GetMapping("/{id}")
	ResponseEntity<CustomerDetailsResponse> getById(String id);
	
	@GetMapping("/customer/{id}")
	ResponseEntity<CustomerDetailsResponse> getByCustomerId(String customerId);
	
	@GetMapping
	ResponseEntity<Page<CustomerDetailsResponse>> getAll(Pageable pageable);
	
	@PostMapping
	ResponseEntity<CustomerDetailsResponse> create(CustomerDetailsRequest customerDetails);
	
	@PutMapping("/{id}")
	ResponseEntity<CustomerDetailsResponse> update(CustomerDetailsRequest customerDetails, String id);
	
	@DeleteMapping("/{id}")
	ResponseEntity<Void> delete(String id);

}