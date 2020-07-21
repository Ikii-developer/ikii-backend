package mx.ikii.business.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import mx.ikii.business.controller.IBusinessCategoryController;
import mx.ikii.business.service.IBusinessCategoryServiceWrapper;
import mx.ikii.commons.payload.request.business.BusinessCategoryRequest;
import mx.ikii.commons.payload.response.business.BusinessCategoryResponse;

@Component
public class BusinessCategoryControllerImpl implements IBusinessCategoryController {

	@Autowired
	private IBusinessCategoryServiceWrapper businessServiceWrapper;


	@Override
	public ResponseEntity<Page<BusinessCategoryResponse>> getAll(Pageable pageable) {
		return ResponseEntity.ok(businessServiceWrapper.findAll(pageable));
	}
	
	@Override
	public ResponseEntity<BusinessCategoryResponse> getById(@PathVariable String id) {
		return ResponseEntity.ok(businessServiceWrapper.findById(id));
	}

	@Override
	public ResponseEntity<BusinessCategoryResponse> getByBusinesName(@PathVariable String name) {
		return ResponseEntity.ok(businessServiceWrapper.getByBusinesName(name));
	}


	@Override
	public ResponseEntity<BusinessCategoryResponse> create(@RequestBody BusinessCategoryRequest businessCategoryRequest) {
		return ResponseEntity.ok(businessServiceWrapper.create(businessCategoryRequest));
	}

	@Override
	public ResponseEntity<BusinessCategoryResponse> update(@RequestBody BusinessCategoryRequest businessCategoryRequest, @PathVariable String id) {
		return ResponseEntity.ok(businessServiceWrapper.update(businessCategoryRequest, id));
	}

	@Override
	public ResponseEntity<Void> delete(@PathVariable String id) {
		businessServiceWrapper.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
