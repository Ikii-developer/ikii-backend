package mx.ikii.commons.persistence.collection;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "CategoryProduct")
public class CategoryProduct {
	private String id;
	private String name;
	private ObjectId businessId;
	private String image;
	private String type;
	private String description;
	private Integer order;
}
