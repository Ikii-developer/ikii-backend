package mx.ikii.payment.payload.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShippingContactsDTO {
	
	private String phone;
	
	private String receiver;
	
	private Address address;

	@Data
	public static class Address {
		private String street1;
		private String country;
		private String postal_code;
	}
}
