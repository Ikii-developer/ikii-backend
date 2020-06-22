package mx.ikii.payment.service.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.conekta.Customer;
import mx.ikii.payment.conekta.service.customer.ICustomerConektaService;
import mx.ikii.payment.payload.request.CustomerConektaRequest;
import mx.ikii.payment.payload.response.CustomerConektaResponse;
import mx.ikii.payment.payload.response.OrderConektaResponse;

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
		
		// TODO:  CHANGE to Mapper
//		CustomerConektaResponse response = customerConektaMapper.customerConektaToCustomerConektaResponse(customer);
		CustomerConektaResponse response = new CustomerConektaResponse();
		response.setId(customer.id);
		response.setName(customer.name);
		response.setEmail(customer.email);
		response.setPhone(customer.phone);
		
		return response;
	}

	@Override
	public CustomerConektaResponse updateCustomerConekta(CustomerConektaRequest customerConektaRequest) {
		Customer customer = customerConektaService.updateCurstomer(customerConektaRequest);
		
		// TODO:  CHANGE to Mapper
//		CustomerConektaResponse response = customerConektaMapper.customerConektaToCustomerConektaResponse(customer);
		CustomerConektaResponse response = new CustomerConektaResponse();
		response.setId(customer.id);
		response.setName(customer.name);
		response.setEmail(customer.email);
		response.setPhone(customer.phone);
		
		return response;
	}

	@Override
	public CustomerConektaResponse findCustomerConekta(String customerId) {
		Customer customer = customerConektaService.findCurstomer(customerId);
		
		// TODO:  CHANGE to Mapper
//		CustomerConektaResponse response = customerConektaMapper.customerConektaToCustomerConektaResponse(customer);
		CustomerConektaResponse response = new CustomerConektaResponse();
		response.setId(customer.id);
		response.setName(customer.name);
		response.setEmail(customer.email);
		response.setPhone(customer.phone);
		
		return response;
	}
	
	
	
	

}
