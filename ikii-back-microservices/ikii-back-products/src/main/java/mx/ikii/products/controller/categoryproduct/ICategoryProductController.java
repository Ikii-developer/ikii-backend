package mx.ikii.products.controller.categoryproduct;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import mx.ikii.commons.payload.request.categoryproduct.CategoryProductRequest;
import mx.ikii.commons.payload.response.categoryproduct.CategoryProductResponse;

public interface ICategoryProductController {

	@GetMapping("/categories/{id}")
	ResponseEntity<CategoryProductResponse> getById(String id);
	
	@GetMapping("/categories/{name}")
	ResponseEntity<CategoryProductResponse> getByName(String name);
	
	@GetMapping("/categories")
	ResponseEntity<Page<CategoryProductResponse>> getAll(Pageable pageable);
	
	@PostMapping("/categories/}")
	ResponseEntity<CategoryProductResponse> create(CategoryProductRequest request);

	@PutMapping("/categories/{id}")
	ResponseEntity<CategoryProductResponse> update(CategoryProductRequest request, String id);
	
	@DeleteMapping("/categories/{id}")
	ResponseEntity<Void> delete(String id);
	
}
