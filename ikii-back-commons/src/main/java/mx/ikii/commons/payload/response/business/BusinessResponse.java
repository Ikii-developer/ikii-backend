package mx.ikii.commons.payload.response.business;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessResponse {
	private String id;
	private String name;
	private String image;
	private String categoryId;
	private String customerId;
	private String description;
	private String deliveryTime;
	private String closeTime;
	private String status;
	private String postalCode;

}
