package mx.ikii.commons.payload.request.user;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class CustomerRequest implements Serializable{

	private static final long serialVersionUID = 3511156059169211894L;
	
	private String id;
	@NotNull(message = "Name cannot be null")
	private String name;
	@NotNull(message = "Name cannot be null")
	private String lastName;
	private String secondLastName;
	@Email(message = "Email should be valid")
	private String email;
	private String phoneNumber;
	@NotNull(message = "Password cannot be null")
	private String password;
	private String birthday;
	private Boolean isEnabled;

}