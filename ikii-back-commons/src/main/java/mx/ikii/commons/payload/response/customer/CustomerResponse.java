package mx.ikii.commons.payload.response.customer;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is used as the outcoming DTO related the the user response
 * 
 * @author Francisco Javier Mart�nez Arazo
 *
 */
@Data
@Builder
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
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