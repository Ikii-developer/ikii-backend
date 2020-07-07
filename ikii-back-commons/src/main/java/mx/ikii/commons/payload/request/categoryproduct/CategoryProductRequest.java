package mx.ikii.commons.payload.request.categoryproduct;

import java.io.Serializable;

import lombok.Data;

@Data
public class CategoryProductRequest implements Serializable{

	private static final long serialVersionUID = -647780939680173556L;
	
	private String name;
	
	private String picture;
	
	private String type;
	
	private String description;
	
}
