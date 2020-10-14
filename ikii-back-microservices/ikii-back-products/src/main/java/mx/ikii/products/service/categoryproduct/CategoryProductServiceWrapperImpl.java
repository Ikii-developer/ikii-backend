package mx.ikii.products.service.categoryproduct;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.ikii.commons.mapper.categoryproduct.ICategoryProductMapper;
import mx.ikii.commons.payload.request.categoryproduct.CategoryProductRequest;
import mx.ikii.commons.payload.response.categoryproduct.CategoryProductResponse;
import mx.ikii.commons.persistence.collection.CategoryProduct;
import mx.ikii.commons.utils.PageHelper;

@Slf4j
@Service
public class CategoryProductServiceWrapperImpl implements ICategoryProductServiceWrapper {

	@Autowired
	private ICategoryProductService categoryProductService;

	@Autowired
	private ICategoryProductMapper categoryProductMapper;

	@Override
	public CategoryProductResponse findById(String id) {
		return categoryProductMapper.entityToResponse(categoryProductService.findById(id));
	}

	@Override
	public List<CategoryProductResponse> findByParentCategoryId(String productCategoryId) {
		return categoryProductMapper.entityToResponse(categoryProductService.findByParentCategoryId(productCategoryId));
	}

	@Override
	public List<CategoryProductResponse> findByBusinessId(String businessId) {
		return categoryProductMapper.entityToResponse(categoryProductService.findByBusinessId(businessId));
	}

	@Override
	public CategoryProductResponse findByName(String name) {
		return categoryProductMapper.entityToResponse(categoryProductService.findByName(name));
	}

	@Override
	public Page<CategoryProductResponse> findAll(Pageable pageable) {
		Page<CategoryProduct> categoryProduct = categoryProductService.findAll(pageable);
		List<CategoryProductResponse> categoryProductResponses = categoryProductMapper
				.entityToResponse(categoryProduct.getContent());
		return PageHelper.createPage(categoryProductResponses, pageable, categoryProduct.getTotalElements());
	}

	@Override
	public CategoryProductResponse create(CategoryProductRequest categoryProductRequest) {
		log.info("Creating categoryProduct {}", categoryProductRequest);
		CategoryProduct categoryProduct = categoryProductMapper.requestToEntity(categoryProductRequest);
		return categoryProductMapper.entityToResponse(categoryProductService.create(categoryProduct));
	}

	@Override
	public CategoryProductResponse update(CategoryProductRequest categoryProductRequest, String id) {
		CategoryProduct categoryProduct = categoryProductMapper.requestToEntity(categoryProductRequest);
		return categoryProductMapper.entityToResponse(categoryProductService.update(categoryProduct, id));
	}

	@Override
	public void delete(String id) {
		categoryProductService.delete(id);
	}

	@Override
	public List<CategoryProductResponse> createBulk(List<CategoryProductRequest> categoryProductRequest) {
		return categoryProductRequest.stream().map(productCategory -> {
			return this.create(productCategory);
		}).collect(Collectors.toList());
	}

}
