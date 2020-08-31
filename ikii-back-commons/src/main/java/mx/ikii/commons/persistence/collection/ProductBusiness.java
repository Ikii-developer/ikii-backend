package mx.ikii.commons.persistence.collection;

import java.math.BigDecimal;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "ProductBusiness", language = "es")
public class ProductBusiness {

	@Id
	private String id;
	
	private ObjectId businessId;

	private String productCategory;

	private String measureUnit;

	private String code;
	
	@TextIndexed(weight = 2)
	private String name;
	
	@TextIndexed(weight = 1)
	private String description;

	private Map<String, String> fullDescription; // for Food

	private BigDecimal price;

	private String pathImage;

}
