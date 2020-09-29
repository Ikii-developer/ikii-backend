package mx.ikii.commons.mapper.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.mapstruct.Mapper;

import mx.ikii.commons.mapper.utils.GenericMapper;
import mx.ikii.commons.mapper.utils.StringObjectIdMapper;
import mx.ikii.commons.payload.request.product.ProductBusinessRequest;
import mx.ikii.commons.payload.response.product.ProductBusinessResponse;
import mx.ikii.commons.payload.response.product.ProductGroupingByBusiness;
import mx.ikii.commons.persistence.collection.ProductBusiness;

@Mapper(componentModel = "spring", uses = { StringObjectIdMapper.class })
public interface IProductBusinessMapper extends GenericMapper<ProductBusiness, ProductBusinessRequest, ProductBusinessResponse> {
	
	
	default List<ProductGroupingByBusiness> entityToProductGroupingByBusiness(Map<ObjectId, List<ProductBusiness>> productsByBusiness){
		List<ProductGroupingByBusiness> productByBusiness = new ArrayList<>();
		
		productsByBusiness.forEach( (k,v)->{
			ProductGroupingByBusiness productGroupingByBusiness = new ProductGroupingByBusiness();
			productGroupingByBusiness.setBusinessId(k.toHexString());
			productGroupingByBusiness.setProducts(entityToResponse(v));
			productByBusiness.add(productGroupingByBusiness);
		} );
		return productByBusiness;
	}

}
