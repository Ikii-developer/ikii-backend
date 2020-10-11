package mx.ikii.products.service.categoryproduct;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.ikii.commons.payload.request.categoryproduct.CategoryProductRequest;
import mx.ikii.commons.payload.response.categoryproduct.CategoryProductResponse;

public interface ICategoryProductServiceWrapper {

	CategoryProductResponse findById(String id);

	List<CategoryProductResponse> findByParentCategoryId(String productCategoryId);

	CategoryProductResponse findByName(String name);

	List<CategoryProductResponse> findByBusinessId(String businessId);

	Page<CategoryProductResponse> findAll(Pageable pageable);

	CategoryProductResponse create(CategoryProductRequest categoryProductRequest);

	List<CategoryProductResponse> createBulk(List<CategoryProductRequest> categoryProductRequest);

	CategoryProductResponse update(CategoryProductRequest categoryProductRequest, String id);

	void delete(String id);

}
