package mx.ikii.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mx.ikii.commons.mapper.business.IBusinessCategoryMapper;
import mx.ikii.commons.payload.request.business.BusinessCategoryRequest;
import mx.ikii.commons.payload.response.business.BusinessCategoryResponse;
import mx.ikii.commons.persistence.collection.BusinessCategory;
import mx.ikii.commons.utils.PageHelper;

@Service
public class BusinessCategoryServiceWrapperImpl implements IBusinessCategoryServiceWrapper {

	@Autowired
	private IBusinessCategoryMapper businessCategoryMapper;

	@Autowired
	private IBusinessCategoryService businessCategoryService;

	@Override
	public BusinessCategoryResponse findById(String id) {
		return businessCategoryMapper.entityToResponse(businessCategoryService.findById(id));
	}

	@Override
	public BusinessCategoryResponse getByBusinesName(String name) {
		return businessCategoryMapper.entityToResponse(businessCategoryService.findByUserName(name));
	}

	@Override
	public Page<BusinessCategoryResponse> findAll(Pageable pageable) {
		Page<BusinessCategory> businessCategories = businessCategoryService.findAll(pageable);
		List<BusinessCategoryResponse> usersResponse = businessCategoryMapper
				.entityToResponse(businessCategories.getContent());
		return PageHelper.createPage(usersResponse, pageable, businessCategories.getTotalElements());
	}

	@Override
	public BusinessCategoryResponse create(BusinessCategoryRequest businessCategoryRequest) {
		BusinessCategory businessCategory = businessCategoryMapper.requestToEntity(businessCategoryRequest);
		return businessCategoryMapper.entityToResponse(businessCategoryService.create(businessCategory));
	}

	@Override
	public BusinessCategoryResponse update(BusinessCategoryRequest businessCategoryRequest, String id) {
		BusinessCategory businessCategory = businessCategoryMapper.requestToEntity(businessCategoryRequest);
		return businessCategoryMapper.entityToResponse(businessCategoryService.update(businessCategory, id));
	}

	@Override
	public void delete(String id) {
		businessCategoryService.delete(id);
	}

}
