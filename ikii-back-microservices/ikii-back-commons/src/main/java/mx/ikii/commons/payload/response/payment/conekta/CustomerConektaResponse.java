package mx.ikii.commons.payload.response.payment.conekta;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * https://developers.conekta.com/api?language=java#customer
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerConektaResponse {
	
	private String id;
	
	private String name;
	
	private String email;
	
	private String phone;
	
	private String default_card_id;
	
	private String default_shipping_contact_id;
	
	private String default_payment_source_id;

}
