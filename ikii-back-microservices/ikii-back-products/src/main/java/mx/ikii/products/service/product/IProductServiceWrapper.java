package mx.ikii.products.service.product;

import java.util.List;

import mx.ikii.commons.payload.request.product.ProductFilter;
import mx.ikii.commons.payload.response.product.ProductModelResponse;

public interface IProductServiceWrapper {

	List<ProductModelResponse> searchByKeywords(ProductFilter productFilter);

}
