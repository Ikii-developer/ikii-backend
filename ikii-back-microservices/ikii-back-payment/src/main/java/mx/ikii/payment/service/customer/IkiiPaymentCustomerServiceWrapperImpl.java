package mx.ikii.payment.service.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.conekta.Customer;
import mx.ikii.payment.mapper.CustomerConektaMapper;
import mx.ikii.payment.methods.conekta.service.customer.ICustomerConektaService;
import mx.ikii.payment.payload.request.CustomerConektaRequest;
import mx.ikii.payment.payload.response.CustomerConektaResponse;

@Service
public class IkiiPaymentCustomerServiceWrapperImpl implements IKiiPaymentCustomerServiceWrapper {
	
//	@Autowired
//	private ICustomerOpenPayService customerOpenPayService;
	
	@Autowired
	private ICustomerConektaService customerConektaService;
	
//	@Autowired
//	private ICustomerConektaMapper customerConektaMapper;
	
	@Override
	public CustomerConektaResponse createCustomerConekta(CustomerConektaRequest customerConektaRequest) {
		Customer customer = customerConektaService.createCurstomer(customerConektaRequest);
		CustomerConektaResponse response = CustomerConektaMapper.customerConektaToCustomerConektaResponse(customer);
		return response;
	}

	@Override
	public CustomerConektaResponse updateCustomerConekta(CustomerConektaRequest customerConektaRequest) {
		Customer customer = customerConektaService.updateCurstomer(customerConektaRequest);
		CustomerConektaResponse response = CustomerConektaMapper.customerConektaToCustomerConektaResponse(customer);
		return response;
	}

	@Override
	public CustomerConektaResponse findCustomerConekta(String customerId) {
		Customer customer = customerConektaService.findCurstomer(customerId);
		CustomerConektaResponse response = CustomerConektaMapper.customerConektaToCustomerConektaResponse(customer);
		return response;
	}
	
	
	
	

}
