package mx.ikii.payment.payload.response;

import lombok.Data;

/**
 * https://developers.conekta.com/api?language=java#customer
 */
@Data
public class CustomerConektaResponse {
	
	private String id;
	
	private String name;
	
	private String email;
	
	private String phone;
	
	private String default_shipping_contact_id;
	
	private String default_payment_source_id;

}
