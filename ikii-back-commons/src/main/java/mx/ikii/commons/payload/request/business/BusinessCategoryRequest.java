package mx.ikii.commons.payload.request.business;

import java.io.Serializable;

import lombok.Data;

@Data
public class BusinessCategoryRequest implements Serializable{
	private static final long serialVersionUID = -4518072196012491851L;

	private String name;
	
	private String picture;
	
	private String type;
	
	private String description;
	
}
