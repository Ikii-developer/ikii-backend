
package mx.ikii.business.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.ikii.commons.payload.request.business.BusinessRateRequest;
import mx.ikii.commons.payload.request.business.RateRequest;
import mx.ikii.commons.payload.response.business.BusinessRateResponse;

@RestController
@RequestMapping("/rates")
public interface IBusinessRateController {

	@GetMapping("/{businessId}")
	ResponseEntity<BusinessRateResponse> findByBusinessId(String businessId);

	@PostMapping
	ResponseEntity<BusinessRateResponse> create(BusinessRateRequest businessRate);

	@PutMapping
	ResponseEntity<BusinessRateResponse> update(BusinessRateRequest businessRate);
	
	@PutMapping("/rate/{businessId}")
	ResponseEntity<BusinessRateResponse> updateRate(RateRequest rate, String businessId);

	@PostMapping("/rate/{businessId}")
	ResponseEntity<BusinessRateResponse> createRate(RateRequest rate, String businessId);

	@DeleteMapping("/{idRate}/{businessId}")
	ResponseEntity<Void> deleteRate(String idRate, String businessId);

	@DeleteMapping("/{id}")
	ResponseEntity<Void> delete(String id);
	
}
