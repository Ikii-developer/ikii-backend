package mx.ikii.commons.persistence.collection;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * This class is used as the entity for the Customer resource and represents the
 * Customer collection in the database
 * 
 * @author Francisco Javier Mart�nez Arazo
 *
 */
@ToString
@Data
@Document(collection = "Customer")
@JsonInclude(Include.NON_NULL)
public class Customer implements Serializable {
	private static final long serialVersionUID = -5507907273019766759L;

	@Id
	private String id;
	private String name;
	private String lastName;
	private String secondLastName;
	private String email;
	private String phoneNumber;
	private String password;
	private String birthday;
	private String image;
	private Boolean isEnabled = Boolean.TRUE;
	private Boolean accountNonExpired = Boolean.TRUE;
	private Boolean credentialNonExpired = Boolean.TRUE;
	private Boolean accountNonLocked = Boolean.TRUE;

	@DBRef
	private Set<Role> roles = new HashSet<>();
}
