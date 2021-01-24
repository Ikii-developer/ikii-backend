package mx.ikii.commons.persistence.collection;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "Business", language = "es")
public class Business {

	@Id
	private String id;
	private ObjectId customerId;
	
	@TextIndexed(weight = 2)
	private String name;
	private String image;
	private ObjectId categoryId;
	
	@TextIndexed(weight = 1)
	private String description;
	private String deliveryTime;
	private String closeTime;	
	private Boolean isOpen;
	private String status;
	private String postalCode;
	private Double deliveryRange;
	private String telephoneNumber;

}
