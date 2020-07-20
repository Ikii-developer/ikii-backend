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

import mx.ikii.commons.payload.request.business.BusinessCategoryRequest;
import mx.ikii.commons.payload.response.business.BusinessCategoryResponse;

@RestController
@RequestMapping("/category")
public interface IBusinessCategoryController {

	@GetMapping
	ResponseEntity<Page<BusinessCategoryResponse>> getAll(Pageable pageable);

	@GetMapping("{id}")
	ResponseEntity<BusinessCategoryResponse> getById(String id);

	@GetMapping("/business/{name}")
	ResponseEntity<BusinessCategoryResponse> getByBusinesName(String name);

	@PostMapping
	ResponseEntity<BusinessCategoryResponse> create(BusinessCategoryRequest businessCategoryRequest);

	@PutMapping("{id}")
	ResponseEntity<BusinessCategoryResponse> update(BusinessCategoryRequest businessCategoryRequest, String id);

	@DeleteMapping("{id}")
	ResponseEntity<Void> delete(String id);
	
}
