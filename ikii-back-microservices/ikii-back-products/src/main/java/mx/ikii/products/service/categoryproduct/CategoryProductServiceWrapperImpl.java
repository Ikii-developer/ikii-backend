package mx.ikii.products.service.categoryproduct;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mx.ikii.commons.mapper.categoryproduct.ICategoryProductMapper;
import mx.ikii.commons.payload.request.categoryproduct.CategoryProductRequest;
import mx.ikii.commons.payload.response.categoryproduct.CategoryProductResponse;
import mx.ikii.commons.persistence.collection.CategoryProduct;
import mx.ikii.commons.utils.PageHelper;

@Service
public class CategoryProductServiceWrapperImpl implements ICategoryProductServiceWrapper{

	@Autowired
	private CategoryProductServiceImpl categoryProductServiceImpl;
	
	@Autowired
	private ICategoryProductMapper categoryProductMapper;
	
	@Override
	public CategoryProductResponse findById(String id) {
		return categoryProductMapper.entityToResponse(categoryProductServiceImpl.findById(id));
	}

	@Override
	public CategoryProductResponse findByName(String name) {
		return categoryProductMapper.entityToResponse(categoryProductServiceImpl.findByName(name));
	}

	@Override
	public Page<CategoryProductResponse> findAll(Pageable pageable) {
		Page<CategoryProduct> categoryProduct = categoryProductServiceImpl.findAll(pageable);
		List<CategoryProductResponse> categoryProductResponses = categoryProductMapper.entityToResponse(categoryProduct.getContent());
		return PageHelper.createPage(categoryProductResponses, pageable, categoryProduct.getTotalElements());
	}

	@Override
	public CategoryProductResponse create(CategoryProductRequest request) {
		CategoryProduct categoryProduct = categoryProductMapper.requestToEntity(request);
		return categoryProductMapper.entityToResponse(categoryProductServiceImpl.create(categoryProduct));
	}

	@Override
	public CategoryProductResponse update(CategoryProductRequest request, String id) {
		CategoryProduct categoryProduct = categoryProductMapper.requestToEntity(request);
		return categoryProductMapper.entityToResponse(categoryProductServiceImpl.update(categoryProduct,id));
	}

	@Override
	public void delete(String id) {
		categoryProductServiceImpl.delete(id);
	}
	
}
