package mx.ikii.products.service.categoryproduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mx.ikii.commons.exception.handler.ResourceNotFoundException;
import mx.ikii.commons.persistence.collection.CategoryProduct;
import mx.ikii.products.repository.categoryproduct.ICategoryProductRepository;

@Service
public class CategoryProductServiceImpl implements ICategoryProductService{

	@Autowired
	private ICategoryProductRepository categoryProductRepository;
	
	@Override
	public CategoryProduct findById(String id) {
		return categoryProductRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id,CategoryProduct.class));
	}

	@Override
	public CategoryProduct findByName(String name) {
		return categoryProductRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException(name,CategoryProduct.class));
	}

	@Override
	public Page<CategoryProduct> findAll(Pageable pageable) {
		return categoryProductRepository.findAll(pageable);
	}

	@Override
	public CategoryProduct create(CategoryProduct request) {
		return categoryProductRepository.insert(request);
	}

	@Override
	public CategoryProduct update(CategoryProduct request, String id) {
		categoryProductRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id,CategoryProduct.class));
		return categoryProductRepository.save(request);
	}

	@Override
	public void delete(String id) {
		categoryProductRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id,CategoryProduct.class));
		categoryProductRepository.deleteById(id);
	}

}
