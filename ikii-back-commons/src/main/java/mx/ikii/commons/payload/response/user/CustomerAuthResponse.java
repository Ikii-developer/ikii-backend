package mx.ikii.commons.payload.response.user;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import mx.ikii.commons.persistence.collection.Role;

@Data
@JsonInclude(Include.NON_NULL)
public class CustomerAuthResponse {

	private String name;
	private String lastName;
	private String secondLastName;
	private String email;
	private String phoneNumber;
	private String password;
	private String birthday;
	private Boolean isEnabled;

	private Set<Role> roles = new HashSet<>();

}
