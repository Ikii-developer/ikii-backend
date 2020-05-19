package mx.ikii.commons.persistence.collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "Privilege")
public class Privilege {

	@Id
	private String id;
	private String name;
	private String description;
}
