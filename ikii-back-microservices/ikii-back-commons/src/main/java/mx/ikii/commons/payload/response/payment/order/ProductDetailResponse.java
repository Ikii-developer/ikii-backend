package mx.ikii.commons.payload.response.payment.order;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDetailResponse {
	
	private Integer quantity;
	private BigDecimal totalAmount;
	private BigDecimal unitAmmount;
	private BigDecimal productPrice;
	private String productName;
	private String productDescription;
	private String productId;

}
