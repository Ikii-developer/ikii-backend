package mx.ikii.commons.mapper.categoryproduct;

import org.mapstruct.Mapper;

import mx.ikii.commons.mapper.utils.GenericMapper;
import mx.ikii.commons.mapper.utils.StringObjectIdMapper;
import mx.ikii.commons.payload.request.categoryproduct.CategoryProductRequest;
import mx.ikii.commons.payload.response.categoryproduct.CategoryProductResponse;
import mx.ikii.commons.persistence.collection.CategoryProduct;

@Mapper(componentModel = "spring", uses = { StringObjectIdMapper.class })
public interface ICategoryProductMapper extends GenericMapper<CategoryProduct, CategoryProductRequest, CategoryProductResponse> {
	
}
