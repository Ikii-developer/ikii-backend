package mx.ikii.products.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.ikii.commons.payload.request.product.ProductFilter;
import mx.ikii.commons.persistence.collection.Product;

public interface IProductService {

	Product findById(String id);

	Product findByName(String productName);

	Page<Product> findAll(Pageable pageable);

	Product create(Product product);

	Product update(Product product, String id);

	void delete(String id);
	
	List<Product> findAllByBussinessId(Pageable pageable, ObjectId bussinessId);
	
	List<Product> filterProduct(Pageable pageable, ProductFilter productFilter);

}
