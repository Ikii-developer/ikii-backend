package mx.ikii.products.controller.categoryproduct;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import mx.ikii.commons.payload.request.categoryproduct.CategoryProductRequest;
import mx.ikii.commons.payload.response.categoryproduct.CategoryProductResponse;
import mx.ikii.products.service.categoryproduct.ICategoryProductServiceWrapper;

@Component
public class CategoryProductControllerImpl implements ICategoryProductController {

	@Autowired
	private ICategoryProductServiceWrapper categoryProductServiceWrapper;

	@Override
	public ResponseEntity<CategoryProductResponse> getById(@PathVariable String id) {
		return ResponseEntity.ok(categoryProductServiceWrapper.findById(id));
	}

	@Override
	public ResponseEntity<List<CategoryProductResponse>> getByBusinessId(@PathVariable String businessId) {
		return ResponseEntity.ok(categoryProductServiceWrapper.findByBusinessId(businessId));
	}

	@Override
	public ResponseEntity<CategoryProductResponse> getByName(@PathVariable String name) {
		return ResponseEntity.ok(categoryProductServiceWrapper.findByName(name));
	}

	@Override
	public ResponseEntity<Page<CategoryProductResponse>> getAll(Pageable pageable) {
		return ResponseEntity.ok(categoryProductServiceWrapper.findAll(pageable));
	}

	@Override
	public ResponseEntity<CategoryProductResponse> create(@RequestBody CategoryProductRequest categoryProductRequest) {
		return ResponseEntity.ok(categoryProductServiceWrapper.create(categoryProductRequest));
	}

	@Override
	public ResponseEntity<CategoryProductResponse> update(@RequestBody CategoryProductRequest categoryProductRequest,
			@PathVariable("id") String id) {
		return ResponseEntity.ok(categoryProductServiceWrapper.update(categoryProductRequest, id));
	}

	@Override
	public ResponseEntity<Void> delete(String id) {
		categoryProductServiceWrapper.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@Override
	public ResponseEntity<List<CategoryProductResponse>> createBulk(
			@RequestBody List<CategoryProductRequest> categoryProductRequest) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(categoryProductServiceWrapper.createBulk(categoryProductRequest));
	}

}
