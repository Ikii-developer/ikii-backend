package mx.ikii.products.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.ikii.commons.payload.request.product.ProductRequest;
import mx.ikii.commons.payload.response.product.ProductResponse;

public interface IProductServiceWrapper {

	ProductResponse findById(String id);

	ProductResponse getByProductName(String name);

	Page<ProductResponse> findAll(Pageable pageable);

	ProductResponse create(ProductRequest productRequest);

	ProductResponse update(ProductRequest productRequest, String id);

	void delete(String id);

	
}
