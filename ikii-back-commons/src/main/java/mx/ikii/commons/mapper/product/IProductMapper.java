package mx.ikii.commons.mapper.product;

import org.mapstruct.Mapper;

import mx.ikii.commons.mapper.utils.GenericMapper;
import mx.ikii.commons.mapper.utils.StringObjectIdMapper;
import mx.ikii.commons.payload.request.product.ProductRequest;
import mx.ikii.commons.payload.response.product.ProductResponse;
import mx.ikii.commons.persistence.collection.Product;

@Mapper(componentModel = "spring", uses = { StringObjectIdMapper.class })
public interface IProductMapper extends GenericMapper<Product, ProductRequest, ProductResponse> {

}
