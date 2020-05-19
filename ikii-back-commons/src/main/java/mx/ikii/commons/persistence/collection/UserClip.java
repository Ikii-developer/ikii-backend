package mx.ikii.commons.persistence.collection;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * This class is used as the entity for the Transaction resource and represents
 * the TransactionClip collection in the database
 * 
 * @author Arturo Isaac Velázquez Vargas
 *
 */
@Data
@Document(collection = "UserClip")
@JsonInclude(Include.NON_NULL)
public class UserClip {

	@Id
	private String id;

	private String userName;

	private String password;

	@DBRef
	private Set<Role> roles = new HashSet<>();

	private boolean enabled = true;

}
