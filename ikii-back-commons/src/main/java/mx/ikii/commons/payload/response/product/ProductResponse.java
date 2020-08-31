package mx.ikii.commons.payload.response.product;

import java.math.BigDecimal;
import java.util.Map;

import org.bson.types.ObjectId;

import lombok.Data;

@Data
public class ProductResponse {
	private String id;
	private String productCategory;
	private String measureUnit;
	private ObjectId bussinessId;
	private String code;
	private String name;
	private String description;
	private Map<String, String> fullDescription; // for Food
	private BigDecimal price;
	private String pathImage;

}
