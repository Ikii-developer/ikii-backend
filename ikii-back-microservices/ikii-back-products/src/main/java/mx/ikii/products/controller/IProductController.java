package mx.ikii.products.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.ikii.commons.payload.request.product.ProductRequest;
import mx.ikii.commons.payload.response.product.ProductResponse;

@RestController
@RequestMapping("/")
public interface IProductController {

	@GetMapping
	ResponseEntity<Page<ProductResponse>> getAll(Pageable pageable);

	@GetMapping("{id}")
	ResponseEntity<ProductResponse> getById(String id);

	@GetMapping("/product/{name}")
	ResponseEntity<ProductResponse> getByProductName(String name);

	@PostMapping
	ResponseEntity<ProductResponse> create(ProductRequest productRequest);

	@PutMapping("{id}")
	ResponseEntity<ProductResponse> update(ProductRequest productRequest, String id);

	@DeleteMapping("{id}")
	ResponseEntity<Void> delete(String id);
	
}
