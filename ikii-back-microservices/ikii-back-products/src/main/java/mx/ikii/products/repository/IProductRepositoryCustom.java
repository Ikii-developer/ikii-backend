package mx.ikii.products.repository;

import java.util.List;

import mx.ikii.commons.persistence.collection.Product;

public interface IProductRepositoryCustom {
	
	List<Product> searchByKeywords(String keyword);

}
