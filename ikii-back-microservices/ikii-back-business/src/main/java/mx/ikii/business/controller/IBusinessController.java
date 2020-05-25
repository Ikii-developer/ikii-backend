package mx.ikii.business.controller;

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


	@GetMapping("{id}")
	ResponseEntity<BusinessResponse> getById(String id);

	@GetMapping("/username/{userName}")
	ResponseEntity<BusinessResponse> getByUserName(String userName);

	@GetMapping
	ResponseEntity<Page<BusinessResponse>> getAll(Pageable pageable);

	@PostMapping
	ResponseEntity<BusinessResponse> create(BusinessRequest userRequest);

	@PutMapping("{id}")
	ResponseEntity<BusinessResponse> update(BusinessRequest userRequest, String id);

	@DeleteMapping("{id}")
	ResponseEntity<Void> delete(String id);
	
}
