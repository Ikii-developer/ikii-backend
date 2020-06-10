package mx.ikii.products.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import mx.ikii.commons.payload.request.product.ProductRequest;
import mx.ikii.commons.payload.response.product.ProductResponse;
import mx.ikii.products.service.IProductServiceWrapper;

@Component
public class ProductControllerImpl implements IProductController {

	@Autowired
	private IProductServiceWrapper productServiceWrapper;


	@Override
	public ResponseEntity<Page<ProductResponse>> getAll(Pageable pageable) {
		return ResponseEntity.ok(productServiceWrapper.findAll(pageable));
	}
	
	@Override
	public ResponseEntity<ProductResponse> getById(@PathVariable String id) {
		return ResponseEntity.ok(productServiceWrapper.findById(id));
	}

	@Override
	public ResponseEntity<ProductResponse> getByProductName(@PathVariable String productName) {
		return ResponseEntity.ok(productServiceWrapper.getByProductName(productName));
	}


	@Override
	public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest productRequest) {
		return ResponseEntity.ok(productServiceWrapper.create(productRequest));
	}

	@Override
	public ResponseEntity<ProductResponse> update(@RequestBody ProductRequest productRequest, @PathVariable String id) {
		return ResponseEntity.ok(productServiceWrapper.update(productRequest, id));
	}

	@Override
	public ResponseEntity<Void> delete(@PathVariable String id) {
		productServiceWrapper.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
