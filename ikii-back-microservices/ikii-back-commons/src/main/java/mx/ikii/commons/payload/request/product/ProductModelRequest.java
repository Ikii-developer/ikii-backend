package mx.ikii.commons.payload.request.product;

import java.math.BigDecimal;
import java.util.Map;

import lombok.Data;

@Data
public class ProductModelRequest {
	
	private String id;
	
	private String productCategory;
	
	private String measureUnit;
	
	private String bussinessId;	
	
	private String code;
	
	private String name;
	
	private String description;
	
	private Map<String, String> fullDescription; // for Food
	
	private BigDecimal price;
	
	private String pathImage;
	

}
