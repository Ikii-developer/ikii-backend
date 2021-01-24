package mx.ikii.commons.persistence.collection;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "Role")
@Data
public class Role {

	private String id;
	private String name;
	private String description;
	private Set<Privilege> privileges = new HashSet<>(0);

}
