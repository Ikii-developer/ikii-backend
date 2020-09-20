package mx.ikii.products.service.productbusiness;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.ikii.commons.payload.request.product.ProductBusinessRequest;
import mx.ikii.commons.payload.request.product.ProductFilter;
import mx.ikii.commons.payload.response.product.ProductBusinessResponse;

public interface IProductBusinessServiceWrapper {

	ProductBusinessResponse findById(String id);

	ProductBusinessResponse getByProductName(String name);

	Page<ProductBusinessResponse> findAll(Pageable pageable);

	ProductBusinessResponse create(ProductBusinessRequest productRequest);

	List<ProductBusinessResponse> createBulk(List<ProductBusinessRequest> productRequest);

	ProductBusinessResponse update(ProductBusinessRequest productRequest, String id);

	void delete(String id);

	List<ProductBusinessResponse> filterProduct(Pageable pageable, ProductFilter productFilter);
}
