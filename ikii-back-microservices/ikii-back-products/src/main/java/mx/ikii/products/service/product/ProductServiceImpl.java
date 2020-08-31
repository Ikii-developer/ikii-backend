package mx.ikii.products.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.ikii.commons.payload.request.product.ProductFilter;
import mx.ikii.commons.persistence.collection.ProductModel;
import mx.ikii.products.repository.product.IProductRepositoryCustom;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductRepositoryCustom productRepositoryCustom;

	@Override
	public List<ProductModel> searchByKeywords(ProductFilter productFilter) {
		return productRepositoryCustom.searchByKeywords(productFilter.getKeywords());
	}
	
}
