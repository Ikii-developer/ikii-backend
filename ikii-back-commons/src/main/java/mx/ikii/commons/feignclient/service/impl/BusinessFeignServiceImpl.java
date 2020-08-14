package mx.ikii.commons.feignclient.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.ikii.commons.feignclient.repository.IBusinessFeignRepository;
import mx.ikii.commons.mapper.business.IBusinessCategoryMapper;
import mx.ikii.commons.mapper.business.IBusinessMapper;
import mx.ikii.commons.persistence.collection.Business;
import mx.ikii.commons.persistence.collection.BusinessCategory;
import mx.ikii.commons.utils.ResponseEntityHelper;

@Service
public class BusinessFeignServiceImpl implements IBusinessFeignService {

	@Autowired
	private IBusinessFeignRepository businessFeignRespository;

	@Autowired
	private IBusinessCategoryMapper businessCategortyMapper;
	
	@Autowired
	private IBusinessMapper businessMapper;
	

	@Override
	public BusinessCategory getByCategoryId(String categoryId) {
		return businessCategortyMapper.responseToEntity(
				ResponseEntityHelper.processingHttpStatus(businessFeignRespository.getByCategoryId(categoryId)));
	}

	@Override
	public Business getById(String id) {
		return businessMapper.responseToEntity(
				ResponseEntityHelper.processingHttpStatus(businessFeignRespository.getById(id)));
	}

}
