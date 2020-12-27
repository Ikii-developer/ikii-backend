package mx.ikii.commons.payload.response.product;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import mx.ikii.commons.payload.response.business.BusinessResponse;

@Data
@Builder
public class ProductGroupingByBusiness {
	
	private BusinessResponse business;
	private List<ProductBusinessResponse> products;
	
}
