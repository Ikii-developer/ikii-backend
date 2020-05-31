package mx.ikii.products.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.ikii.commons.persistence.collection.Product;

public interface IProductService {

	Product findById(String id);

	Product findByName(String productName);

	Page<Product> findAll(Pageable pageable);

	Product create(Product product);

	Product update(Product product, String id);

	void delete(String id);

}
