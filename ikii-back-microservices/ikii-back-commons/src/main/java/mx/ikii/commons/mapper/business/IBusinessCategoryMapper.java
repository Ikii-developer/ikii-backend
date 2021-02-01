package mx.ikii.commons.mapper.business;

import org.mapstruct.Mapper;

import mx.ikii.commons.mapper.utils.GenericMapper;
import mx.ikii.commons.mapper.utils.StringObjectIdMapper;
import mx.ikii.commons.payload.request.business.BusinessCategoryRequest;
import mx.ikii.commons.payload.response.business.BusinessCategoryResponse;
import mx.ikii.commons.persistence.collection.BusinessCategory;

@Mapper(componentModel = "spring", uses = { StringObjectIdMapper.class })
public interface IBusinessCategoryMapper extends GenericMapper<BusinessCategory, BusinessCategoryRequest, BusinessCategoryResponse> {

}
