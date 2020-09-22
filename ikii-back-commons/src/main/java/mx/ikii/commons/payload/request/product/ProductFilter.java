package mx.ikii.commons.payload.request.product;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class ProductFilter {
	private String keywords;

	@NotEmpty(message = "businessId can not be empty or null")
	private String businessId;

}
