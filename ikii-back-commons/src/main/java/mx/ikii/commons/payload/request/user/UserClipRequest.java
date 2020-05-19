package mx.ikii.commons.payload.request.user;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import mx.ikii.commons.persistence.collection.TransactionClip;

/**
 * This class is used as the incoming DTO with all the information related to
 * the clip user
 * 
 * @author Arturo Velázquez Vargas
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
public class UserClipRequest {

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	@NotNull
	private String userName;

	@NotNull
	private String password;

	@NotNull
	private Integer age;

	private String occupation;

	private List<TransactionClip> transactions;

}