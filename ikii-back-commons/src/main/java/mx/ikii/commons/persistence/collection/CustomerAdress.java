package mx.ikii.commons.persistence.collection;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import mx.ikii.commons.payload.dto.Location;
import mx.ikii.commons.utils.constants.EnumCity;

@Data
@Document(collection = "CustomerAddress")
@JsonInclude(Include.NON_NULL)
public class CustomerAdress implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private ObjectId customerId;
	private ObjectId bussinesId;
	private Boolean isMain;
	private String postalCode;
	private String street;
	private String colony;
	private EnumCity city;
	private Integer interiorNumber;
	private Integer exteriorNumber;
	private String description;
	private Boolean isValidate;
	private String nickname;
	private Location location;
	private Double distance;
	private Boolean isCurrent;
}
