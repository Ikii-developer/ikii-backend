package mx.ikii.commons.persistence.collection.util;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductDetail {
	
	private Integer quantity;
	private BigDecimal totalAmount;
	private BigDecimal unitAmmount;
	private BigDecimal productPrice;
	private String productName;
	private String productDescription;
	private String productId;

}
