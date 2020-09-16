package mx.ikii.products.service.product;

import java.util.List;

import mx.ikii.commons.payload.request.product.ProductFilter;
import mx.ikii.commons.persistence.collection.ProductModel;

public interface IProductService {

	List<ProductModel> searchByKeywords(ProductFilter productFilter);
	
}
