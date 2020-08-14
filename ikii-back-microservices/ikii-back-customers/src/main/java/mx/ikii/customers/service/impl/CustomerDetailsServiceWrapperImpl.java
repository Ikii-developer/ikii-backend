package mx.ikii.customers.service.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.ikii.commons.exception.handler.ResourceNotFoundException;
import mx.ikii.commons.feignclient.service.impl.IBusinessFeignService;
import mx.ikii.commons.mapper.customer.ICustomerDetailsMapper;
import mx.ikii.commons.payload.request.customer.CustomerDetailsRequest;
import mx.ikii.commons.payload.response.customer.CustomerDetailsResponse;
import mx.ikii.commons.persistence.collection.Business;
import mx.ikii.commons.persistence.collection.CustomerDetails;
import mx.ikii.commons.utils.PageHelper;
import mx.ikii.customers.helper.CustomerDetailsHelper;
import mx.ikii.customers.service.ICustomerDetailsService;
import mx.ikii.customers.service.ICustomerDetailsServiceWrapper;

@Service
@Slf4j
public class CustomerDetailsServiceWrapperImpl implements ICustomerDetailsServiceWrapper {

	@Autowired
	private ICustomerDetailsService customerDetailsService;

	@Autowired
	private IBusinessFeignService businessFeignService;

	@Autowired
	private ICustomerDetailsMapper customerDetailsMapper;

	@Override
	public CustomerDetailsResponse getById(String id) {
		return customerDetailsMapper.entityToResponse(customerDetailsService.getById(id));
	}

	@Override
	public CustomerDetailsResponse getByCustomerId(String customerId) {
		return customerDetailsMapper.entityToResponse(customerDetailsService.getByCustomerId(customerId));
	}

	@Override
	public Page<CustomerDetailsResponse> getAll(Pageable pageable) {
		List<CustomerDetailsResponse> response = customerDetailsMapper
				.entityToResponse(customerDetailsService.getAll(pageable));
		return PageHelper.createPage(response, pageable, new Long(response.size()));
	}

	@Override
	public CustomerDetailsResponse create(CustomerDetailsRequest customerDetailsRequest) {
		CustomerDetails customerDetails = customerDetailsMapper.requestToEntity(customerDetailsRequest);
		return customerDetailsMapper.entityToResponse(customerDetailsService.save(customerDetails));
	}

	@Override
	public CustomerDetailsResponse update(CustomerDetailsRequest customerDetailsRequest, String id) {
		CustomerDetails customerDetails = customerDetailsMapper.requestToEntity(customerDetailsRequest);
		return customerDetailsMapper.entityToResponse(customerDetailsService.update(customerDetails, id));
	}

	@Override
	public void toggleFavorite(String customerId, String businessId) {
		CustomerDetails customerDetailsEntity = customerDetailsService.getByCustomerId(customerId);
		try {
			businessFeignService.getById(businessId);
		} catch (Exception e) {
			log.error("Not possible to get Business for businessId {}, {}", businessId, e.getMessage());
			throw new ResourceNotFoundException(businessId, Business.class);
		}
		CustomerDetailsHelper.toggleFavorites(customerDetailsEntity.getBusinessFavorites(), new ObjectId(businessId));
		customerDetailsService.save(customerDetailsEntity);
	}

	@Override
	public void delete(String id) {
		customerDetailsService.delete(id);
	}

}
