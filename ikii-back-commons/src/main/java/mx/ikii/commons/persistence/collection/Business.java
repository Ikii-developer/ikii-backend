package mx.ikii.commons.persistence.collection;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "Business")
public class Business {

	@Id
	private String id;
	private ObjectId customerId;
	private String name;
	private String image;
	private ObjectId categoryId;
	private String description;
	private String deliveryTime;
	private String closeTime;	
	private String status;
	private String postalCode;

}
