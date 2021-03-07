package mx.ikii.commons.feignclient.service.impl;

import mx.ikii.commons.payload.request.customer.CustomerDetailsRequest;
import mx.ikii.commons.persistence.collection.CustomerDetails;

public interface ICustomerDetailsFeignService {
  CustomerDetails update(CustomerDetailsRequest customerDetailsRequest, String id);

  CustomerDetails findByCustomerId(String customerId);

}
