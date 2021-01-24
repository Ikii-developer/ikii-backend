package mx.ikii.commons.mapper.business;

import org.mapstruct.Mapper;

import mx.ikii.commons.mapper.utils.GenericMapper;
import mx.ikii.commons.mapper.utils.StringObjectIdMapper;
import mx.ikii.commons.payload.request.business.RateRequest;
import mx.ikii.commons.payload.response.business.RateResponse;
import mx.ikii.commons.persistence.collection.Rate;

@Mapper(componentModel = "spring", uses = { StringObjectIdMapper.class })
public interface IRateMapper extends GenericMapper<Rate, RateRequest, RateResponse> {

}