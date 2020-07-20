package mx.ikii.commons.persistence.collection;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Business {
	
	@Id	
	private String id;
	
	private ObjectId customerId;
	
	private String name;
	
	private String image;
	
	private String category;
	
	private String description;
	
	private String postalCode;

}
