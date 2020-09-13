package mx.ikii.commons.persistence.collection;

import java.math.BigDecimal;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Product {

	@Id
	private String id;
	private ObjectId productCategoryId;
	private String measureUnit;
	private ObjectId bussinessId;
	private String code;
	private String name;
	private String description;
	private Map<String, String> fullDescription; // for Food
	private BigDecimal price;
	private String pathImage;

}
