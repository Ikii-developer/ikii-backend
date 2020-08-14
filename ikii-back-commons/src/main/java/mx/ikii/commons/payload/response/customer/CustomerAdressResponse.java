package mx.ikii.commons.payload.response.customer;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import mx.ikii.commons.payload.dto.LocationDTO;
import mx.ikii.commons.utils.constants.EnumCity;

/**
 * This class is the address of any type customer 
 */
@Data
@JsonInclude(Include.NON_NULL)
public class CustomerAdressResponse implements Serializable{
	private static final long serialVersionUID = 4399190531074934234L;
	
	private String id;
	
	private String customerId;
	
	private String businessId;
	
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
	
//	public LocationDTO locationDTO;
	
	private Double distance;
	
	private Boolean isCurrent;
	
}	