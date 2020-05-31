package mx.ikii.products.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mx.ikii.commons.exception.handler.helper.ThrowsException;
import mx.ikii.commons.persistence.collection.Product;
import mx.ikii.products.repository.IProductRepository;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductRepository productRepository;

	@Override
	public Product findById(String id) {
		Optional<Product> product = productRepository.findById(id);
		return ThrowsException.resourceNotFound(product, id, Product.class);
	}

	@Override
	public Product findByName(String name) {
		Product product = productRepository.findByName(name);
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

		Product product = productRepository.findById(id).orElse(null);
		ThrowsException.resourceNotFound(Optional.ofNullable(product), id, Product.class);
		productRepository.save(productToUpdate);
		return product;
	}

	@Override
	public void delete(String id) {
		Product product = productRepository.findById(id).orElse(null);
		ThrowsException.resourceNotFound(Optional.ofNullable(product), id, Product.class);
		productRepository.deleteById(id);
	}

}
