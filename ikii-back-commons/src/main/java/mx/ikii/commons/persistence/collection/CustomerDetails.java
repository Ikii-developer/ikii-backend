package mx.ikii.commons.persistence.collection;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@Document(collection = "CustomerDetails")
@JsonInclude(Include.NON_NULL)
public class CustomerDetails {

	private ObjectId customerId;
	private Boolean isValidAccount;
	private String image;
	private Integer type;
	
}
