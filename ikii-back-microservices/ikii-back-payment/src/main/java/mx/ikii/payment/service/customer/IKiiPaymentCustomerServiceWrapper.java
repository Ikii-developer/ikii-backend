package mx.ikii.payment.service.customer;

import mx.ikii.payment.payload.request.CustomerConektaRequest;
import mx.ikii.payment.payload.response.CustomerConektaResponse;

public interface IKiiPaymentCustomerServiceWrapper {

	CustomerConektaResponse createCustomerConekta(CustomerConektaRequest customerConektaRequest);
	
	CustomerConektaResponse updateCustomerConekta(CustomerConektaRequest customerConektaRequest);
	
	CustomerConektaResponse findCustomerConekta(String customerId);
	
}
