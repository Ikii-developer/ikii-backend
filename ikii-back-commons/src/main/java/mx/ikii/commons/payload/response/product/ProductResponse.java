package mx.ikii.commons.payload.response.product;

import java.math.BigDecimal;
import java.util.Map;

import lombok.Data;

@Data
public class ProductResponse {
	private String id;
	private String productCategoryId;
	private String measureUnit;
	private String bussinessId;
	private String code;
	private String name;
	private String description;
	private Map<String, String> fullDescription; // for Food
	private BigDecimal price;
	private String pathImage;

}
