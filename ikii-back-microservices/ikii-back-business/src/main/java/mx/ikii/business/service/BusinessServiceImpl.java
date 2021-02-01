package mx.ikii.business.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import mx.ikii.business.helper.Helper;
import mx.ikii.business.repository.IBusinessRepository;
import mx.ikii.business.repository.custom.IBusinessRepositoryCustom;
import mx.ikii.commons.exception.handler.ResourceNotFoundException;
import mx.ikii.commons.persistence.collection.Business;

@Service
public class BusinessServiceImpl implements IBusinessService {

	@Autowired
	private IBusinessRepository businessRepository;
	
	@Autowired
	private IBusinessRepositoryCustom businessRepositoryCustom;

	@Override
	public Business findById(String id) {
		Business business = businessRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id, Business.class));
		return business;
	}

	@Override
	public Business findByName(String name) {
		Business business = businessRepository.findByName(name);
		return business;
	}
	
	@Override
	public List<Business> filterByBusinessName(String name) {
		List<Business> business = businessRepositoryCustom.filterByBusinessName(name);
		return business;
	}

	@Override
	public Page<Business> findAll(Pageable pageable) {
		return businessRepository.findAll(pageable);
	}

	@Override
	public Business create(Business business) {
		return businessRepository.insert(business);
	}

	@Override
	public Business update(Business business, String id) {
		Business businessEntity = businessRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id, Business.class));
		business.setId(id);
		Helper.setUpdateProperties(businessEntity, business);
		businessRepository.save(businessEntity);
		return businessEntity;
	}

	@Override
	public void delete(String id) {
		businessRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, Business.class));
		businessRepository.deleteById(id);
	}

	@Override
	public Business findByCustomerId(String customerId) {
		List<Business> businesses = businessRepository.findByCustomerId(new ObjectId(customerId));
		if(businesses.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Business found with customerId " + customerId);
		}else {
			return businesses.get(0);
		}
	}

}
