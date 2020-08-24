package mx.ikii.products.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mx.ikii.commons.mapper.product.IProductMapper;
import mx.ikii.commons.payload.request.product.ProductFilter;
import mx.ikii.commons.payload.request.product.ProductRequest;
import mx.ikii.commons.payload.response.product.ProductResponse;
import mx.ikii.commons.persistence.collection.Product;
import mx.ikii.commons.utils.Nullable;
import mx.ikii.commons.utils.PageHelper;

@Service
public class ProductServiceWrapperImpl implements IProductServiceWrapper {

	@Autowired
	private IProductMapper productMapper;

	@Autowired
	private IProductService productService;

	@Override
	public ProductResponse findById(String id) {
		return productMapper.entityToResponse(productService.findById(id));
	}

	@Override
	public ProductResponse getByProductName(String productName) {
		return productMapper.entityToResponse(productService.findByName(productName));
	}

	@Override
	public Page<ProductResponse> findAll(Pageable pageable) {
		Page<Product> product = productService.findAll(pageable);
		List<ProductResponse> productResponse = productMapper.entityToResponse(product.getContent());

		return PageHelper.createPage(productResponse, pageable, product.getTotalElements());
	}

	@Override
	public ProductResponse create(ProductRequest productRequest) {
		Product product = productMapper.requestToEntity(productRequest);
		return productMapper.entityToResponse(productService.create(product));
	}

	@Override
	public ProductResponse update(ProductRequest productRequest, String id) {
		Product product = productMapper.requestToEntity(productRequest);
		return productMapper.entityToResponse(productService.update(product, id));
	}

	@Override
	public void delete(String id) {
		productService.delete(id);
	}

	@Override
	public List<ProductResponse> filterProduct(Pageable pageable, ProductFilter productFilter) {
		List<Product> products = null;
		
		products = Nullable.isNullOrEmpty(productFilter.getBusinessId())?
			productService.findAllByBussinessId(pageable, new ObjectId(productFilter.getBusinessId())) : 
			productService.filterProduct(pageable, productFilter);
			
		List<ProductResponse> productResponse = productMapper.entityToResponse(products);

		return productResponse;
	}

}
