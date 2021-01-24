package mx.ikii.commons.payload.request.customer;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * This class is to define type customer and additional data
 * 
 * @author Francisco Javier Mart�nez Arazo
 *
 */

@Data
@JsonInclude(Include.NON_NULL)
public class CustomerDetailsRequest {
	private String id;
	private String customerId;
	private Boolean isValidAccount;
	private String image;
	private Integer type;
	private List<String> businessFavorites;
}
