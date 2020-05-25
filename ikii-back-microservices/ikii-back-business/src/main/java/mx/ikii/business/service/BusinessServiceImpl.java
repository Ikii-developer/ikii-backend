package mx.ikii.business.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mx.ikii.business.repository.IBusinessRepository;
import mx.ikii.commons.exception.handler.helper.ThrowsException;
import mx.ikii.commons.persistence.collection.Business;

@Service
public class BusinessServiceImpl implements IBusinessService {

	@Autowired
	private IBusinessRepository businessRepository;

	@Override
	public Business findById(String id) {
		Optional<Business> business = businessRepository.findById(id);
		return ThrowsException.resourceNotFound(business, id, Business.class);
	}

	@Override
	public Business findByUserName(String userName) {
		Business business = businessRepository.findByName(userName);
		return business;
	}

	@Override
	public Page<Business> findAll(Pageable pageable) {
		return businessRepository.findAll(pageable);
	}

	@Override
	public Business create(Business user) {
		return businessRepository.insert(user);
	}

	@Override
	public Business update(Business user, String id) {

		Business business = businessRepository.findById(id).orElse(null);
		ThrowsException.resourceNotFound(Optional.ofNullable(business), id, Business.class);
		businessRepository.save(business);
		return business;
	}

	@Override
	public void delete(String id) {
		Business business = businessRepository.findById(id).orElse(null);
		ThrowsException.resourceNotFound(Optional.ofNullable(business), id, Business.class);
		businessRepository.deleteById(id);
	}

}
