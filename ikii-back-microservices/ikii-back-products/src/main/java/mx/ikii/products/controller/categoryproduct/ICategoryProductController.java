package mx.ikii.products.controller.categoryproduct;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.ikii.commons.payload.request.categoryproduct.CategoryProductRequest;
import mx.ikii.commons.payload.response.categoryproduct.CategoryProductResponse;

@RestController
@RequestMapping("/category")
public interface ICategoryProductController {

	@GetMapping("/{id}")
	ResponseEntity<CategoryProductResponse> getById(String id);
	
	@GetMapping("/name/{name}")
	ResponseEntity<CategoryProductResponse> getByName(String name);
	
	@GetMapping
	ResponseEntity<Page<CategoryProductResponse>> getAll(Pageable pageable);
	
	@PostMapping
	ResponseEntity<CategoryProductResponse> create(CategoryProductRequest categoryProductRequest);

	@PutMapping("/{id}")
	ResponseEntity<CategoryProductResponse> update(CategoryProductRequest categoryProductRequest, String id);
	
	@DeleteMapping
	ResponseEntity<Void> delete(String id);
	
}
