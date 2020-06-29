package mx.ikii.payment.conekta.service.customer;

import io.conekta.Customer;
import mx.ikii.payment.payload.request.CustomerConektaRequest;

public interface ICustomerConektaService {
	
	Customer createCurstomer(CustomerConektaRequest customerConektaRequest);
	
	Customer updateCurstomer(CustomerConektaRequest customerConektaRequest);
	
	Customer findCurstomer(String customerId);
	
}
