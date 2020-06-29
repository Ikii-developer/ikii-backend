package mx.ikii.payment.mapper;

import io.conekta.Customer;
import mx.ikii.payment.payload.response.CustomerConektaResponse;

public class CustomerConektaMapper {
	
	
	public static CustomerConektaResponse customerConektaToCustomerConektaResponse(Customer customer) {
		CustomerConektaResponse response = new CustomerConektaResponse();
		response.setId(customer.id);
		response.setName(customer.name);
		response.setEmail(customer.email);
		response.setPhone(customer.phone);
		response.setDefault_card_id(customer.default_card_id);
		response.setDefault_payment_source_id(customer.default_payment_source_id);
//		response.setDefault_shipping_contact_id(customer.shipping_contacts);
		return response;
	}

}
