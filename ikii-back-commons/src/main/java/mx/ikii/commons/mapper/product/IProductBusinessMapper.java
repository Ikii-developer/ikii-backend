package mx.ikii.commons.mapper.product;

import org.mapstruct.Mapper;

import mx.ikii.commons.mapper.utils.GenericMapper;
import mx.ikii.commons.mapper.utils.StringObjectIdMapper;
import mx.ikii.commons.payload.request.product.ProductBusinessRequest;
import mx.ikii.commons.payload.response.product.ProductBusinessResponse;
import mx.ikii.commons.persistence.collection.ProductBusiness;

@Mapper(componentModel = "spring", uses = { StringObjectIdMapper.class })
public interface IProductBusinessMapper extends GenericMapper<ProductBusiness, ProductBusinessRequest, ProductBusinessResponse> {

}
