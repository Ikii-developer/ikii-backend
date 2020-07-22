package mx.ikii.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mx.ikii.business.repository.IBusinessCategoryRepository;
import mx.ikii.commons.exception.handler.ResourceNotFoundException;
import mx.ikii.commons.persistence.collection.Business;
import mx.ikii.commons.persistence.collection.BusinessCategory;

@Service
public class BusinessCategoryServiceImpl implements IBusinessCategoryService {

	@Autowired
	private IBusinessCategoryRepository businessRepository;

	@Override
	public BusinessCategory findById(String id) {
		BusinessCategory business = businessRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id, Business.class));
		return business;
	}

	@Override
	public BusinessCategory findByUserName(String name) {
		BusinessCategory business = businessRepository.findByName(name);
		return business;
	}

	@Override
	public Page<BusinessCategory> findAll(Pageable pageable) {
		return businessRepository.findAll(pageable);
	}

	@Override
	public BusinessCategory create(BusinessCategory businessCategory) {
		return businessRepository.insert(businessCategory);
	}

	@Override
	public BusinessCategory update(BusinessCategory businessCategory, String id) {
		businessRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id, Business.class));
		businessCategory.setId(id);
		return businessRepository.save(businessCategory);
	}

	@Override
	public void delete(String id) {
		businessRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, Business.class));
		businessRepository.deleteById(id);
	}

}
