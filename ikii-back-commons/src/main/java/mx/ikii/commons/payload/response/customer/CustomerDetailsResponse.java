package mx.ikii.commons.payload.response.customer;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class CustomerDetailsResponse {
	private String id;
	private String customerId;
	private Boolean isValidAccount;
	private String image;
	private Integer type;
	private List<String> businessFavorites;

}
