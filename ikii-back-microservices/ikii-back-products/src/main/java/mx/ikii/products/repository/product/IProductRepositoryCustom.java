package mx.ikii.products.repository.product;

import java.util.List;

import mx.ikii.commons.persistence.collection.ProductModel;

public interface IProductRepositoryCustom {
	
	List<ProductModel> searchByKeywords(String keyword);

}
