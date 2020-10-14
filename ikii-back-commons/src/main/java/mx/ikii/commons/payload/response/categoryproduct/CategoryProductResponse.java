package mx.ikii.commons.payload.response.categoryproduct;

import lombok.Data;

@Data
public class CategoryProductResponse {
	private String id;
	private String name;
	private String businessId;
	private String parentProductCategoryId;
	private Boolean isParent;
	private String image;
	private String type;
	private String description;
	private Integer order;

}
