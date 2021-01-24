package mx.ikii.commons.payload.request.order;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class OrderDetailRequest {
		
	private String productId;
	private BigDecimal amount;
	private BigDecimal productPrice;
	private String productName;
	private String productDescription;
 
}
