package mx.ikii.commons.payload.response.user;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * This class is used as the outcoming DTO related the the user response
 * 
 * @author Francisco Javier Mart�nez Arazo
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
public class CustomerResponse implements Serializable {

	private static final long serialVersionUID = 4409947770631003418L;

	private String id;
	private String name;
	private String lastName;
	private String secondLastName;
	private String email;
	private String phoneNumber;
	private String birthday;

}