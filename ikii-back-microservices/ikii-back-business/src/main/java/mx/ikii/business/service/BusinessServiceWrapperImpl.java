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
	public BusinessResponse findByUseName(String userName) {
		return businessMapper.entityToResponse(businessService.findByUserName(userName));
	}

	@Override
	public Page<BusinessResponse> findAll(Pageable pageable) {
		Page<Business> userClip = businessService.findAll(pageable);
		List<BusinessResponse> usersResponse = businessMapper.entityToResponse(userClip.getContent());

		return PageHelper.createPage(usersResponse, pageable, userClip.getTotalElements());
	}

	@Override
	public BusinessResponse create(BusinessRequest userRequest) {
		Business userEntity = businessMapper.requestToEntity(userRequest);
		return businessMapper.entityToResponse(businessService.create(userEntity));
	}

	@Override
	public BusinessResponse update(BusinessRequest userRequest, String id) {
		Business userEntity = businessMapper.requestToEntity(userRequest);
		return businessMapper.entityToResponse(businessService.update(userEntity, id));
	}

	@Override
	public void delete(String id) {
		businessService.delete(id);
	}

}
