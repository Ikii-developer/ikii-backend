package mx.ikii.commons.payload.response.customer;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class CustomerDetailsResponse implements Serializable{
	
	private static final long serialVersionUID = 1093093193518619781L;
	
	private String customerId;
	private Boolean isValidAccount;
	private String image;
	private Integer type;
	
}
