package mx.ikii.commons.mapper.product;

import org.mapstruct.Mapper;

import mx.ikii.commons.mapper.utils.GenericMapper;
import mx.ikii.commons.mapper.utils.StringObjectIdMapper;
import mx.ikii.commons.payload.request.product.ProductModelRequest;
import mx.ikii.commons.payload.response.product.ProductModelResponse;
import mx.ikii.commons.persistence.collection.ProductModel;

@Mapper(componentModel = "spring", uses = { StringObjectIdMapper.class })
public interface IProductMapper extends GenericMapper<ProductModel, ProductModelRequest, ProductModelResponse> {

}
