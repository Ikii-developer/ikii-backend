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

import mx.ikii.commons.payload.request.customer.CustomerAdressRequest;
import mx.ikii.commons.payload.response.customer.CustomerAdressResponse;

@RestController
@RequestMapping("/")
public interface IAddressController {

	@GetMapping("/adress/{id}")
	ResponseEntity<CustomerAdressResponse> getById(String id);
	
	@GetMapping("/adress/{name}")
	ResponseEntity<Page<CustomerAdressResponse>> getByCustomerId(String customerId,Pageable pageable);
	
	@PostMapping("/adress/{id}")
	ResponseEntity<CustomerAdressResponse> create(CustomerAdressRequest request);
	
	@PutMapping("/adress/{id}")
	ResponseEntity<CustomerAdressResponse> update(CustomerAdressRequest request, String id);
	
	@DeleteMapping("/adress/{id}")
	ResponseEntity<Void> delete(String id);
	
}
