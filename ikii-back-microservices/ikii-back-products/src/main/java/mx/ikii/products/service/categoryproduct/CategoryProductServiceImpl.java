package mx.ikii.products.service.categoryproduct;

import java.util.Collections;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mx.ikii.commons.exception.handler.ResourceNotFoundException;
import mx.ikii.commons.persistence.collection.CategoryProduct;
import mx.ikii.products.repository.categoryproduct.ICategoryProductRepository;

@Service
public class CategoryProductServiceImpl implements ICategoryProductService {

	@Autowired
	private ICategoryProductRepository categoryProductRepository;

	@Override
	public CategoryProduct findById(String id) {
		return categoryProductRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id, CategoryProduct.class));
	}

	@Override
	public List<CategoryProduct> findByParentCategoryId(String productCategoryId) {
		return categoryProductRepository.findByParentProductCategoryId(new ObjectId(productCategoryId))
				.orElse(Collections.emptyList());
	}

	@Override
	public List<CategoryProduct> findByBusinessId(String businessId) {
		return categoryProductRepository.findByBusinessId(new ObjectId(businessId)).orElse(Collections.emptyList());
	}

	@Override
	public CategoryProduct findByName(String name) {
		return categoryProductRepository.findByName(name)
				.orElseThrow(() -> new ResourceNotFoundException(name, CategoryProduct.class));
	}

	@Override
	public Page<CategoryProduct> findAll(Pageable pageable) {
		return categoryProductRepository.findAll(pageable);
	}

	@Override
	public CategoryProduct create(CategoryProduct categoryProduct) {
		return categoryProductRepository.insert(categoryProduct);
	}

	@Override
	public CategoryProduct update(CategoryProduct categoryProduct, String id) {
		categoryProductRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id, CategoryProduct.class));
		return categoryProductRepository.save(categoryProduct);
	}

	@Override
	public void delete(String id) {
		categoryProductRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id, CategoryProduct.class));
		categoryProductRepository.deleteById(id);
	}

}
