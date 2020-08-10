package mx.ikii.business.service;

import mx.ikii.commons.payload.request.business.BusinessRateRequest;
import mx.ikii.commons.payload.request.business.RateRequest;
import mx.ikii.commons.payload.response.business.BusinessRateResponse;

public interface IBusinessRateServiceWrapper {

	BusinessRateResponse findByBusinessId(String businessId);

	BusinessRateResponse create(BusinessRateRequest businessRate);

	BusinessRateResponse update(BusinessRateRequest businessRate);
	
	BusinessRateResponse updateRate(RateRequest rate, String businessId);

	BusinessRateResponse createRate(RateRequest rate, String businessId);

	void deleteRate(String idRate, String businessId);

	void delete(String id);

	
}
