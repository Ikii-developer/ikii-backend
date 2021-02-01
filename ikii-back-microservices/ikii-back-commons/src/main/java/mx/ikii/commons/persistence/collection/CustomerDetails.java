package mx.ikii.commons.persistence.collection;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "CustomerDetails")
@JsonInclude(Include.NON_NULL)
public class CustomerDetails {

	@Id
	private String id;
	private ObjectId customerId;
	private Boolean isValidAccount;
	private String image;
	private Integer type;
	private List<ObjectId> businessFavorites;
	private List<ObjectId> productFavorites;

}
