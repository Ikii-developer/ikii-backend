package mx.ikii.products.service.categoryproduct;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.ikii.commons.persistence.collection.CategoryProduct;

public interface ICategoryProductService {
	
	CategoryProduct findById(String id);
	
	CategoryProduct findByName(String name);
	
	Page<CategoryProduct> findAll(Pageable pageable);
	
	CategoryProduct create(CategoryProduct request);
	
	CategoryProduct update(CategoryProduct request, String id);
	
	void delete(String id);
}
