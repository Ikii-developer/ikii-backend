package mx.ikii.commons.payload.response.product;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductModelResponse {
	private String id;
	private String code;
	private String name;
	private String description;
	private BigDecimal price;
	private String pathImage;

}
