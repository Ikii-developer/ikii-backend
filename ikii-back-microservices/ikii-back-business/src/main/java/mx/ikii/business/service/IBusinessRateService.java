package mx.ikii.business.service;

import mx.ikii.commons.persistence.collection.BusinessRate;
import mx.ikii.commons.persistence.collection.Rate;

public interface IBusinessRateService {

	BusinessRate findByBusinessId(String businessId);

	BusinessRate create(BusinessRate businessRate);

	BusinessRate update(BusinessRate businessRate);
	
	BusinessRate updateRate(Rate rate, String businessId);

	BusinessRate createRate(Rate rate,String businessId);

	void deleteRate(String idRate, String businessId);

	void delete(String id);

}
