package mx.ikii.commons.payload.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * Lista de los productos que se venden en la orden.  
 * 
 * unit_price: El precio por unidad expresado en centavos.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LineItemsDTO {
	
	private String id;
	
	private String name;
	
	private String description;
	
	private BigDecimal unit_price;
	
	private Integer quantity;
	
	private List<String> tags;
	
	private String type;

}
