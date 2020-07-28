package mx.ikii.commons.payload.request.business;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class BusinessRequest {

	private String id;
	@NotNull(message = "name can not be null")
	private String name;

	@NotNull(message = "image can not be null")
	private String image;

	@NotNull(message = "categoryId can not be null")
	private String categoryId;

	@NotNull(message = "customerId can not be null")
	private String customerId;

	@NotNull(message = "description can not be null")
	private String description;

	private String postalCode;

}
