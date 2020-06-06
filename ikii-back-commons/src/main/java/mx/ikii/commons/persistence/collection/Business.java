package mx.ikii.commons.persistence.collection;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Business {
	
	private String id;
	
	private String name;
	
	private String image;
	
	private String category;
	
	private String description;
	
	private String postalCode;

}
