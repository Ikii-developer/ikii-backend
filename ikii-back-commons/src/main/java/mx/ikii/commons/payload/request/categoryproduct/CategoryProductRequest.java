package mx.ikii.commons.payload.request.categoryproduct;

import lombok.Data;

@Data
public class CategoryProductRequest {
	private String name;
	private String image;
	private String businessId;
	private String type;
	private String description;
	private Integer order;

}
