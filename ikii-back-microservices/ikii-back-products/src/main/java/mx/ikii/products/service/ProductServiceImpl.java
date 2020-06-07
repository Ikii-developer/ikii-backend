package mx.ikii.products.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mx.ikii.commons.exception.handler.ResourceNotFoundException;
import mx.ikii.commons.persistence.collection.Product;
import mx.ikii.products.repository.IProductRepository;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductRepository productRepository;

	@Override
	public Product findById(String id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id, Product.class));
		return product;
	}

	@Override
	public Product findByName(String name) {
		Product product = productRepository.findByName(name)
				.orElseThrow(() -> new ResourceNotFoundException(name, Product.class));
		return product;
	}

	@Override
	public Page<Product> findAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	@Override
	public Product create(Product product) {
		return productRepository.insert(product);
	}

	@Override
	public Product update(Product productToUpdate, String id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id, Product.class));
		productRepository.save(productToUpdate);
		return product;
	}

	@Override
	public void delete(String id) {
		productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, Product.class));
		productRepository.deleteById(id);
	}

}
