package mx.ikii.products.controller.categoryproduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import mx.ikii.commons.payload.request.categoryproduct.CategoryProductRequest;
import mx.ikii.commons.payload.response.categoryproduct.CategoryProductResponse;
import mx.ikii.products.service.categoryproduct.ICategoryProductServiceWrapper;

public class CategoryProductControllerImpl implements ICategoryProductController{
	
	@Autowired
	private ICategoryProductServiceWrapper categoryProductServiceWrapper;
	
	@Override
	public ResponseEntity<CategoryProductResponse> getById(String id){
		return ResponseEntity.ok(categoryProductServiceWrapper.findById(id));
	}
	
	@Override
	public ResponseEntity<CategoryProductResponse> getByName(String name){
		return ResponseEntity.ok(categoryProductServiceWrapper.findByName(name));
	}
	
	@Override
	public ResponseEntity<Page<CategoryProductResponse>> getAll(Pageable pageable){
		return ResponseEntity.ok(categoryProductServiceWrapper.findAll(pageable));
	}

	@Override
	public ResponseEntity<CategoryProductResponse> create(@RequestBody CategoryProductRequest request) {
		return ResponseEntity.ok(categoryProductServiceWrapper.create(request));
	}

	@Override
	public ResponseEntity<CategoryProductResponse> update(@RequestBody CategoryProductRequest request, @PathVariable("id")String id) {
		return ResponseEntity.ok(categoryProductServiceWrapper.update(request, id));
	}

	@Override
	public ResponseEntity<Void> delete(String id) {
		categoryProductServiceWrapper.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
}
