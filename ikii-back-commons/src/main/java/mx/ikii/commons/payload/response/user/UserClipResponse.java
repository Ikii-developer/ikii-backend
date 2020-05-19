package mx.ikii.commons.payload.response.user;

import java.util.List;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import mx.ikii.commons.persistence.collection.Role;

/**
 * This class is used as the outcoming DTO related the the user response
 * 
 * @author Arturo Isaac Velázquez Vargas
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
public class UserClipResponse {

	private String id;

	private String userName;

	private String password;

	@DBRef
	private Set<Role> roles;

}