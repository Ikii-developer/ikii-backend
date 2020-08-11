package mx.ikii.commons.persistence.collection;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "BusinessCategory")
public class BusinessCategory {
	private String id;
	private String name;
	private String picture;
	private String type;
	private String description;
	private String title;
	private String overviewTitle;
	private String sectionTitle;

}
