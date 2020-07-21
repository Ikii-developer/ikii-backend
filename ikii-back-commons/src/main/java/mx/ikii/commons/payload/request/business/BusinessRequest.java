package mx.ikii.commons.payload.request.business;

import lombok.Data;

@Data
public class BusinessRequest {
	
	private String id;
	
	private String name;
	
	private String image;
	
	private String categoryId;
	
	private String description;
	
	private String postalCode;

}
