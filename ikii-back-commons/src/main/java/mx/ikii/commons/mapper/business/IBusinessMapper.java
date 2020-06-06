package mx.ikii.commons.mapper.business;

import org.mapstruct.Mapper;

import mx.ikii.commons.mapper.utils.GenericMapper;
import mx.ikii.commons.mapper.utils.StringObjectIdMapper;
import mx.ikii.commons.payload.request.business.BusinessRequest;
import mx.ikii.commons.payload.response.business.BusinessResponse;
import mx.ikii.commons.persistence.collection.Business;

@Mapper(componentModel = "spring", uses = { StringObjectIdMapper.class })
public interface IBusinessMapper extends GenericMapper<Business, BusinessRequest, BusinessResponse> {

}
