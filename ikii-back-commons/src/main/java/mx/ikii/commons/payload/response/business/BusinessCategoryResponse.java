package mx.ikii.commons.payload.response.business;

import java.io.Serializable;

import lombok.Data;

@Data
public class BusinessCategoryResponse implements Serializable{
	private static final long serialVersionUID = -4518072196012491851L;

	private String id;
	
	private String name;
	
	private String picture;
	
	private String type;
	
	private String description;
	
}
