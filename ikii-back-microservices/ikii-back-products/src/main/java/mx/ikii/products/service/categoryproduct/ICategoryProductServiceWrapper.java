package mx.ikii.products.service.categoryproduct;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.ikii.commons.payload.request.categoryproduct.CategoryProductRequest;
import mx.ikii.commons.payload.response.categoryproduct.CategoryProductResponse;

public interface ICategoryProductServiceWrapper {
	
	CategoryProductResponse findById(String id);
	
	CategoryProductResponse findByName(String name);
	
	Page<CategoryProductResponse> findAll(Pageable pageable);
	
	CategoryProductResponse create(CategoryProductRequest categoryProductRequest);
	
	CategoryProductResponse update(CategoryProductRequest categoryProductRequest, String id);
	
	void delete(String id);
	
}
