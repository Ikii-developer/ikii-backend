package mx.ikii.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.ikii.commons.mapper.business.IBusinessRateMapper;
import mx.ikii.commons.mapper.business.IRateMapper;
import mx.ikii.commons.payload.request.business.BusinessRateRequest;
import mx.ikii.commons.payload.request.business.RateRequest;
import mx.ikii.commons.payload.response.business.BusinessRateResponse;
import mx.ikii.commons.persistence.collection.BusinessRate;
import mx.ikii.commons.persistence.collection.Rate;

@Service
public class BusinessRateServiceWrapperImpl implements IBusinessRateServiceWrapper {
	
	@Autowired
	private IBusinessRateService businessRateService;

	@Autowired
	private IBusinessRateMapper businessRateMapper;
	
	@Autowired
	private IRateMapper rateMapper;

	@Override
	public BusinessRateResponse findByBusinessId(String businessId) {
		BusinessRate businessRate = businessRateService.findByBusinessId(businessId);
		return businessRateMapper.entityToResponse(businessRate);
	}

	@Override
	public BusinessRateResponse create(BusinessRateRequest businessRateRequest) {
		BusinessRate businessRate =  businessRateMapper.requestToEntity(businessRateRequest);
		BusinessRate processedBusinessRate = businessRateService.create(businessRate);
		return businessRateMapper.entityToResponse(processedBusinessRate);
	}

	@Override
	public BusinessRateResponse update(BusinessRateRequest businessRateRequest) {
		BusinessRate businessRate =  businessRateMapper.requestToEntity(businessRateRequest);
		BusinessRate processedBusinessRate = businessRateService.update(businessRate);
		return businessRateMapper.entityToResponse(processedBusinessRate);
	}

	@Override
	public BusinessRateResponse updateRate(RateRequest rateRequest, String businessId) {
		Rate rate = rateMapper.requestToEntity(rateRequest);
		BusinessRate processedBusinessRate = businessRateService.updateRate(rate, businessId);
		return businessRateMapper.entityToResponse(processedBusinessRate);
	}

	@Override
	public BusinessRateResponse createRate(RateRequest rateRequest, String businessId) {
		Rate rate = rateMapper.requestToEntity(rateRequest);
		BusinessRate processedBusinessRate = businessRateService.createRate(rate, businessId);
		return businessRateMapper.entityToResponse(processedBusinessRate);
	}

	@Override
	public void deleteRate(String idRate, String businessId) {
		businessRateService.deleteRate(idRate, businessId);
	}

	@Override
	public void delete(String id) {
		businessRateService.delete(id);
	}

}
