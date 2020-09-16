package mx.ikii.products.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.ikii.commons.mapper.product.IProductMapper;
import mx.ikii.commons.payload.request.product.ProductFilter;
import mx.ikii.commons.payload.response.product.ProductModelResponse;
import mx.ikii.commons.persistence.collection.ProductModel;

@Service
public class ProductServiceWrapperImpl implements IProductServiceWrapper {

	@Autowired
	private IProductMapper productMapper;

	@Autowired
	private IProductService productService;

	@Override
	public List<ProductModelResponse> searchByKeywords(ProductFilter productFilter) {
		List<ProductModel> products = productService.searchByKeywords(productFilter);
		return productMapper.entityToResponse(products);
	}

}
