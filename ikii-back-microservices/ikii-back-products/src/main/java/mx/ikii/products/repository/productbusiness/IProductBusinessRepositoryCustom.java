package mx.ikii.products.repository.productbusiness;

import java.util.List;

import mx.ikii.commons.persistence.collection.ProductBusiness;

public interface IProductBusinessRepositoryCustom {
	
	List<ProductBusiness> searchByKeywords(String keyword);

}
