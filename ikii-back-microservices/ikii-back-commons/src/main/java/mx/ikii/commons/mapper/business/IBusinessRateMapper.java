package mx.ikii.commons.mapper.business;

import org.mapstruct.Mapper;

import mx.ikii.commons.mapper.utils.GenericMapper;
import mx.ikii.commons.mapper.utils.StringObjectIdMapper;
import mx.ikii.commons.payload.request.business.BusinessRateRequest;
import mx.ikii.commons.payload.response.business.BusinessRateResponse;
import mx.ikii.commons.persistence.collection.BusinessRate;

@Mapper(componentModel = "spring", uses = { StringObjectIdMapper.class })
public interface IBusinessRateMapper extends GenericMapper<BusinessRate, BusinessRateRequest, BusinessRateResponse> {

}
