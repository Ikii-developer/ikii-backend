package mx.ikii.commons.payload.response.product;

import java.util.List;

import lombok.Data;

@Data
public class ProductGroupingByBusiness {
	
	private String businessId;
	private List<ProductBusinessResponse> products;
	
}
