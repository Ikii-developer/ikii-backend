package mx.ikii.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mx.ikii.commons.mapper.business.IBusinessMapper;
import mx.ikii.commons.payload.request.business.BusinessRequest;
import mx.ikii.commons.payload.response.business.BusinessResponse;
import mx.ikii.commons.persistence.collection.Business;
import mx.ikii.commons.utils.PageHelper;

@Service
public class BusinessServiceWrapperImpl implements IBusinessServiceWrapper {

	@Autowired
	private IBusinessMapper businessMapper;

	@Autowired
	private IBusinessService businessService;

	@Override
	public BusinessResponse findById(String id) {
		return businessMapper.entityToResponse(businessService.findById(id));
	}

	@Override
	public BusinessResponse getByBusinesName(String businessName) {
		return businessMapper.entityToResponse(businessService.findByName(businessName));
	}

	@Override
	public Page<BusinessResponse> findAll(Pageable pageable) {
		Page<Business> business = businessService.findAll(pageable);
		List<BusinessResponse> businessResponse = businessMapper.entityToResponse(business.getContent());

		return PageHelper.createPage(businessResponse, pageable, business.getTotalElements());
	}

	@Override
	public BusinessResponse create(BusinessRequest businessRequest) {
		Business business = businessMapper.requestToEntity(businessRequest);
		return businessMapper.entityToResponse(businessService.create(business));
	}

	@Override
	public BusinessResponse update(BusinessRequest businessRequest, String id) {
		Business business = businessMapper.requestToEntity(businessRequest);
		return businessMapper.entityToResponse(businessService.update(business, id));
	}

	@Override
	public void delete(String id) {
		businessService.delete(id);
	}

}
