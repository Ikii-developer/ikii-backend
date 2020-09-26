package mx.ikii.business.controller;

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

import mx.ikii.commons.payload.request.business.BusinessRequest;
import mx.ikii.commons.payload.response.business.BusinessResponse;

@RestController
@RequestMapping("/")
public interface IBusinessController {

	@GetMapping
	ResponseEntity<Page<BusinessResponse>> getAll(Pageable pageable, String customerId);

	@GetMapping("{id}")
	ResponseEntity<BusinessResponse> getById(String id);

	@GetMapping("/name/{businessName}")
	ResponseEntity<BusinessResponse> getByBusinesName(String businessName);
	
	@GetMapping("/filter-by-name/{businessName}")
	ResponseEntity<List<BusinessResponse>> filterByBusinessName(String businessName);

	@PostMapping
	ResponseEntity<BusinessResponse> create(BusinessRequest userRequest);

	@PostMapping("/batch")
	ResponseEntity<List<BusinessResponse>> create(List<BusinessRequest> userRequest);

	@PutMapping("{id}")
	ResponseEntity<BusinessResponse> update(BusinessRequest userRequest, String id);

	@DeleteMapping("{id}")
	ResponseEntity<Void> delete(String id);

}
