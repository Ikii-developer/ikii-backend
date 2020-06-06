package mx.ikii.commons.payload.request.user;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class CustomerDetailsRequest implements Serializable{

	private static final long serialVersionUID = -2607453200527489900L;
	
	@NotNull
	private String idCustomer;
	private Boolean isValidAccount;
	private String image;
	@NotNull
	private Integer type;
}
