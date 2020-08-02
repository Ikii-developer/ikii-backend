package mx.ikii.commons.payload.request.customer;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import mx.ikii.commons.payload.dto.Location;
import mx.ikii.commons.utils.constants.EnumCity;

/**
 * This class is the address of any type customer
 */
@Data
@JsonInclude(Include.NON_NULL)
public class CustomerAdressRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@NotEmpty
	private String customerId;
	
	private String bussinesId;
	
	private Boolean isMain;
	
	@NotEmpty
	private String postalCode;
	
	@NotEmpty
	private String street;
	
	@NotEmpty
	private String colony;
	
	@NotNull
	private EnumCity city;
	
	@NotNull
	@NumberFormat
	@Min(1)
	private Integer interiorNumber;
	
	@NotNull
	@NumberFormat
	@Min(1)
	private Integer exteriorNumber;
	
	private String description;
	
	private Boolean isValidate;
	
	@NotNull
	private String nickname;
	
	@NotNull
	private Location location;
	
	@NotNull
	private Boolean isCurrent;
		
}
