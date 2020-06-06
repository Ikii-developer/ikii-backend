package mx.ikii.commons.feignclient.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.ikii.commons.feignclient.repository.ICustomerFeignClientRepository;
import mx.ikii.commons.mapper.user.ICustomerMapper;
import mx.ikii.commons.payload.request.user.CustomerRequest;
import mx.ikii.commons.persistence.collection.Customer;
import mx.ikii.commons.utils.ResponseEntityHelper;

/**
 * @author Arturo Isaac Velazquez Vargas
 */
@Service
public class CustomerFeignServiceImpl implements ICustomerFeignService {

	@Autowired
	private ICustomerFeignClientRepository customerFeignClientRepository;

	@Autowired
	private ICustomerMapper customerMapper;

	@Override
	public Customer getById(String id) {
		return customerMapper
				.responseToEntity(ResponseEntityHelper.processingHttpStatus(customerFeignClientRepository.getById(id)));
	}

	@Override
	public Customer update(CustomerRequest userRequest, String id) {
		return customerMapper.responseToEntity(
				ResponseEntityHelper.processingHttpStatus(customerFeignClientRepository.update(userRequest, id)));
	}

	@Override
	public Customer getByPhoneNumber(String userName) {
		return customerMapper.responseToEntity(
				ResponseEntityHelper.processingHttpStatus(customerFeignClientRepository.getByPhoneNumber(userName)));
	}

	@Override
	public Customer getByEmail(String email) {
		return customerMapper.responseToEntity(
				ResponseEntityHelper.processingHttpStatus(customerFeignClientRepository.getByEmail(email)));
	}

	@Override
	public Customer getByEmailForAuth(String email) {
		return customerMapper.authResponseToentity(
				ResponseEntityHelper.processingHttpStatus(customerFeignClientRepository.getByEmailForAuth(email)));
	}
}
