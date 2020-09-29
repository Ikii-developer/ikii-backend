package mx.ikii.products.service.productbusiness;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mx.ikii.commons.exception.handler.ResourceNotFoundException;
import mx.ikii.commons.payload.request.product.ProductFilter;
import mx.ikii.commons.persistence.collection.ProductBusiness;
import mx.ikii.commons.persistence.collection.ProductModel;
import mx.ikii.products.repository.productbusiness.IProductBusinessRepository;
import mx.ikii.products.repository.productbusiness.IProductBusinessRepositoryCustom;

@Service
public class ProductBusinessServiceImpl implements IProductBusinessService {

	@Autowired
	private IProductBusinessRepository productRepository;
	
	@Autowired
	private IProductBusinessRepositoryCustom productRepositoryCustom;

	@Override
	public ProductBusiness findById(String id) {
		ProductBusiness product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id, ProductModel.class));
		return product;
	}
	
	@Override
	public List<ProductBusiness> findByBusinessIdIn(List<ObjectId> ids) {
		return productRepository.findByBusinessIdIn(ids);
	}

	@Override
	public ProductBusiness findByName(String name) {
		ProductBusiness product = productRepository.findByName(name)
				.orElseThrow(() -> new ResourceNotFoundException(name, ProductModel.class));
		return product;
	}

	@Override
	public Page<ProductBusiness> findAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	@Override
	public ProductBusiness create(ProductBusiness product) {
		return productRepository.insert(product);
	}

	@Override
	public ProductBusiness update(ProductBusiness productToUpdate, String id) {
		ProductBusiness product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id, ProductBusiness.class));
		productToUpdate.setId(id);
		product = productRepository.save(productToUpdate);
		return product;
	}

	@Override
	public void delete(String id) {
		productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, ProductBusiness.class));
		productRepository.deleteById(id);
	}

	@Override
	public List<ProductBusiness> findAllByBussinessId(Pageable pageable, ObjectId bussinessId) {
		return productRepository.findAllByBusinessId(pageable, bussinessId);
	}
	
	@Override
	public List<ProductBusiness> findMostSelledProductsByBussinessId(Pageable pageable, ObjectId bussinessId) {
		return productRepository.findAllByBusinessId(pageable, bussinessId);
	}
	
	@Override
	public List<ProductBusiness> filterProduct(Pageable pageable, ProductFilter productFilter) {
		
		return productRepositoryCustom.searchByKeywords(productFilter.getKeywords());
	}


}
