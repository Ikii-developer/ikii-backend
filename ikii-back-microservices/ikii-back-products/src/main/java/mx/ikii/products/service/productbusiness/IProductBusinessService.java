package mx.ikii.products.service.productbusiness;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.ikii.commons.payload.request.product.ProductFilter;
import mx.ikii.commons.persistence.collection.ProductBusiness;

public interface IProductBusinessService {

	ProductBusiness findById(String id);
	
	List<ProductBusiness> findByBusinessIdIn(List<ObjectId> ids);

	ProductBusiness findByName(String productName);

	Page<ProductBusiness> findAll(Pageable pageable);

	ProductBusiness create(ProductBusiness product);

	ProductBusiness update(ProductBusiness product, String id);

	void delete(String id);
	
	List<ProductBusiness> findAllByBussinessId(Pageable pageable, ObjectId bussinessId);
	
	List<ProductBusiness> findMostSelledProductsByBussinessId(Pageable pageable, ObjectId bussinessId);
	
	List<ProductBusiness> filterProduct(Pageable pageable, ProductFilter productFilter);

}
