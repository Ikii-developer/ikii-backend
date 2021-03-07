package mx.ikii.commons.feignclient.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import mx.ikii.commons.exception.handler.FeignException;
import mx.ikii.commons.feignclient.repository.ICustomerDetailsFeignRepository;
import mx.ikii.commons.mapper.customer.ICustomerDetailsMapper;
import mx.ikii.commons.payload.request.customer.CustomerDetailsRequest;
import mx.ikii.commons.persistence.collection.CustomerDetails;
import mx.ikii.commons.utils.ResponseEntityHelper;

@Slf4j
@Service
public class CustomerDetailsFeignServiceImpl implements ICustomerDetailsFeignService {

  @Autowired
  private ICustomerDetailsFeignRepository customerDetailsFeignRepository;

  @Autowired
  private ICustomerDetailsMapper customerDetailsMapper;

  @Override
  public CustomerDetails update(CustomerDetailsRequest customerDetailsRequest, String id) {
    log.info(
        "[CustomerDetailsFeignServiceImpl] - INIT update customer with request [{}] ",
        customerDetailsRequest);
    try {
      return customerDetailsMapper.responseToEntity(ResponseEntityHelper
          .processingHttpStatus(customerDetailsFeignRepository.update(customerDetailsRequest, id)));
    } catch (Exception e) {
      throw new FeignException(
          String.format(FeignException.MESSAGE, CustomerDetails.class.getName(), e.getMessage()),
          e);

    }
  }

  @Override
  public CustomerDetails findByCustomerId(String id) {
    log.info(
        "[CustomerDetailsFeignServiceImpl] - INIT find by customerId method with customerId [{}]",
        id);
    try {
      return customerDetailsMapper.responseToEntity(ResponseEntityHelper
          .processingHttpStatus(customerDetailsFeignRepository.findByCustomerIdId(id)));
    } catch (Exception e) {
      throw new FeignException(
          String.format(FeignException.MESSAGE, CustomerDetails.class.getName(), e.getMessage()),
          e);

    }
  }

}
