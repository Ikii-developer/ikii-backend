package mx.ikii.business.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import mx.ikii.business.controller.IBusinessRateController;
import mx.ikii.business.service.IBusinessRateServiceWrapper;
import mx.ikii.commons.payload.request.business.BusinessRateRequest;
import mx.ikii.commons.payload.request.business.RateRequest;
import mx.ikii.commons.payload.response.business.BusinessRateResponse;

@Component
public class BusinessRateControllerImpl implements IBusinessRateController {
	
	@Autowired
	private IBusinessRateServiceWrapper businessRateServiceWrapper;

	@Override
	public ResponseEntity<BusinessRateResponse> findByBusinessId(@PathVariable String businessId) {
		BusinessRateResponse businessRate = businessRateServiceWrapper.findByBusinessId(businessId);
		return ResponseEntity.status(HttpStatus.OK).body(businessRate);
	}

	@Override
	public ResponseEntity<BusinessRateResponse> create(@RequestBody BusinessRateRequest businessRateRequest) {
		BusinessRateResponse businessRateResponse = businessRateServiceWrapper.create(businessRateRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(businessRateResponse);
	}

	@Override
	public ResponseEntity<BusinessRateResponse> update(@RequestBody BusinessRateRequest businessRateRequest) {
		BusinessRateResponse businessRateResponse = businessRateServiceWrapper.update(businessRateRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(businessRateResponse);
	}

	@Override
	public ResponseEntity<BusinessRateResponse> updateRate(@RequestBody RateRequest rate, @PathVariable String businessId) {
		BusinessRateResponse businessRateResponse = businessRateServiceWrapper.updateRate(rate, businessId);
		return ResponseEntity.status(HttpStatus.OK).body(businessRateResponse);
	}

	@Override
	public ResponseEntity<BusinessRateResponse> createRate(@RequestBody RateRequest rate, @PathVariable String businessId) {
		BusinessRateResponse businessRateResponse = businessRateServiceWrapper.createRate(rate, businessId);
		return ResponseEntity.status(HttpStatus.CREATED).body(businessRateResponse);
	}

	@Override
	public ResponseEntity<Void> deleteRate(@PathVariable String idRate, @PathVariable String businessId) {
		businessRateServiceWrapper.deleteRate(idRate, businessId);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> delete(@PathVariable String id) {
		businessRateServiceWrapper.delete(id);
		return ResponseEntity.noContent().build();
	}

}
