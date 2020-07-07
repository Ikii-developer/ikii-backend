package mx.ikii.commons.persistence.collection;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@Document(collection = "CustomerDetails")
@JsonInclude(Include.NON_NULL)
public class CustomerDetails {

	private String customerId;
	private Boolean isValidAccount;
	private String image;
	private Integer type;
	
}
