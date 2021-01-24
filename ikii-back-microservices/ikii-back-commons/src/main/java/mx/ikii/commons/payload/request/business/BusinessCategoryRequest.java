package mx.ikii.commons.payload.request.business;

import lombok.Data;

@Data
public class BusinessCategoryRequest {
	private String name;
	private String picture;
	private String type;
	private String description;
	private String overviewTitle;
	private String sectionTitle;

}
