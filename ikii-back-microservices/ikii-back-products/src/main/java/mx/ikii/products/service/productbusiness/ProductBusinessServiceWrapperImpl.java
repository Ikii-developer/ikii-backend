package mx.ikii.products.service.productbusiness;

import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mx.ikii.commons.mapper.product.IProductBusinessMapper;
import mx.ikii.commons.payload.request.product.ProductBusinessRequest;
import mx.ikii.commons.payload.request.product.ProductFilter;
import mx.ikii.commons.payload.response.product.ProductBusinessResponse;
import mx.ikii.commons.persistence.collection.ProductBusiness;
import mx.ikii.commons.utils.Nullable;
import mx.ikii.commons.utils.PageHelper;

@Service
public class ProductBusinessServiceWrapperImpl implements IProductBusinessServiceWrapper {

	@Autowired
	private IProductBusinessMapper productBusinessMapper;

	@Autowired
	private IProductBusinessService productBusinessService;

	@Override
	public ProductBusinessResponse findById(String id) {
		return productBusinessMapper.entityToResponse(productBusinessService.findById(id));
	}

	@Override
	public ProductBusinessResponse getByProductName(String productName) {
		return productBusinessMapper.entityToResponse(productBusinessService.findByName(productName));
	}

	@Override
	public Page<ProductBusinessResponse> findAll(Pageable pageable) {
		Page<ProductBusiness> product = productBusinessService.findAll(pageable);
		List<ProductBusinessResponse> productResponse = productBusinessMapper.entityToResponse(product.getContent());
		return PageHelper.createPage(productResponse, pageable, product.getTotalElements());
	}

	@Override
	public ProductBusinessResponse create(ProductBusinessRequest productRequest) {
		ProductBusiness product = productBusinessMapper.requestToEntity(productRequest);
		return productBusinessMapper.entityToResponse(productBusinessService.create(product));
	}

	@Override
	public List<ProductBusinessResponse> createBulk(List<ProductBusinessRequest> productRequest) {
		return productRequest.stream().map(productRequestMap -> {
			return this.create(productRequestMap);
		}).collect(Collectors.toList());
	}

	@Override
	public ProductBusinessResponse update(ProductBusinessRequest productRequest, String id) {
		ProductBusiness product = productBusinessMapper.requestToEntity(productRequest);
		return productBusinessMapper.entityToResponse(productBusinessService.update(product, id));
	}

	@Override
	public void delete(String id) {
		productBusinessService.delete(id);
	}

	@Override
	public List<ProductBusinessResponse> filterProduct(Pageable pageable, ProductFilter productFilter) {
		List<ProductBusiness> products = null;

		products = Nullable.isNullOrEmpty(productFilter.getBusinessId())
				? productBusinessService.filterProduct(pageable, productFilter)
				: productBusinessService.findAllByBussinessId(pageable, new ObjectId(productFilter.getBusinessId()));

		List<ProductBusinessResponse> productResponse = productBusinessMapper.entityToResponse(products);

		return productResponse;
	}

}
