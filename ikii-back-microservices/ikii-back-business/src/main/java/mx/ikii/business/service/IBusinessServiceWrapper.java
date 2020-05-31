package mx.ikii.business.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.ikii.commons.payload.request.business.BusinessRequest;
import mx.ikii.commons.payload.response.business.BusinessResponse;

public interface IBusinessServiceWrapper {

	BusinessResponse findById(String id);

	BusinessResponse getByBusinesName(String name);

	Page<BusinessResponse> findAll(Pageable pageable);

	BusinessResponse create(BusinessRequest businessRequest);

	BusinessResponse update(BusinessRequest businessRequest, String id);

	void delete(String id);

	
}
