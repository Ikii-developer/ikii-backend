package mx.ikii.customers.controller;

import java.util.List;

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
@RequestMapping("/address")
public interface ICustomerAddressController {

	@GetMapping
	ResponseEntity<Page<CustomerAdressResponse>> getAll(Pageable pageable);
	
	@GetMapping("/{id}")
	ResponseEntity<CustomerAdressResponse> getById(String id);
	
	@GetMapping("/customer/{customerId}")
	ResponseEntity<Page<CustomerAdressResponse>> getByCustomerId(String customerId,Pageable pageable);
	
	@PostMapping
	ResponseEntity<CustomerAdressResponse> create(CustomerAdressRequest customerAdressRequest);
	
	@PutMapping("/{id}")
	ResponseEntity<CustomerAdressResponse> update(CustomerAdressRequest customerAdressRequest, String id);
	
	@DeleteMapping("/{id}")
	ResponseEntity<Void> delete(String id);
	
	@GetMapping("/near-by-me")
	ResponseEntity<List<CustomerAdressResponse>> nearByMe(String latitude, String longitude, Integer distance);
	
	@GetMapping("/near-by-me2")
	ResponseEntity<List<CustomerAdressResponse>> nearByMe2(String latitude, String longitude, Integer distance);
	
	@GetMapping("/near-by-me3")
	ResponseEntity<List<CustomerAdressResponse>> nearByMe3(String latitude, String longitude, Integer distance);
	
}
