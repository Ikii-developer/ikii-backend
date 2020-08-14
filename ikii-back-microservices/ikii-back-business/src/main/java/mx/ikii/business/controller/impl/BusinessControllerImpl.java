package mx.ikii.business.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import mx.ikii.business.controller.IBusinessController;
import mx.ikii.business.service.IBusinessServiceWrapper;
import mx.ikii.commons.payload.request.business.BusinessRequest;
import mx.ikii.commons.payload.response.business.BusinessResponse;

@Component
public class BusinessControllerImpl implements IBusinessController {

	@Autowired
	private IBusinessServiceWrapper businessServiceWrapper;

	@Override
	public ResponseEntity<Page<BusinessResponse>> getAll(Pageable pageable,
			@RequestParam(required = false) String customerId) {
		return ResponseEntity.ok(businessServiceWrapper.findAll(pageable, customerId));
	}

	@Override
	public ResponseEntity<BusinessResponse> getById(@PathVariable String id) {
		return ResponseEntity.ok(businessServiceWrapper.findById(id));
	}

	@Override
	public ResponseEntity<BusinessResponse> getByBusinesName(@PathVariable String businessName) {
		return ResponseEntity.ok(businessServiceWrapper.getByBusinesName(businessName));
	}

	@Override
	public ResponseEntity<BusinessResponse> create(@RequestBody BusinessRequest businessRequest) {
		return ResponseEntity.ok(businessServiceWrapper.create(businessRequest));
	}

	@Override
	public ResponseEntity<List<BusinessResponse>> create(@RequestBody List<BusinessRequest> businessRequest) {
		return ResponseEntity.ok(businessServiceWrapper.create(businessRequest));
	}

	@Override
	public ResponseEntity<BusinessResponse> update(@RequestBody BusinessRequest businessRequest,
			@PathVariable String id) {
		return ResponseEntity.ok(businessServiceWrapper.update(businessRequest, id));
	}

	@Override
	public ResponseEntity<Void> delete(@PathVariable String id) {
		businessServiceWrapper.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
