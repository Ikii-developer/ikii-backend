package mx.ikii.payment.service.customer;

import mx.ikii.commons.payload.request.payment.conekta.CustomerConektaWrapperRequest;
import mx.ikii.payment.payload.request.CustomerConektaRequest;
import mx.ikii.payment.payload.response.CustomerConektaResponse;

public interface IKiiPaymentCustomerServiceWrapper {

  CustomerConektaResponse createCustomerConekta(CustomerConektaWrapperRequest customerConektaRequest);

  CustomerConektaResponse updateCustomerConekta(CustomerConektaRequest customerConektaRequest);

  CustomerConektaResponse findCustomerConekta(String ikiiCustomerId);

}
