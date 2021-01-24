package mx.ikii.commons.payload.response.business;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessCategoryResponse {
	private String id;
	private String name;
	private String picture;
	private String type;
	private String description;
	private String overviewTitle;
	private String sectionTitle;

}
