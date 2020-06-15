package mx.ikii.commons.payload.request.customer;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * This class is to define type customer and additional data
 * 
 * @author Francisco Javier Martínez Arazo
 *
 */

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
