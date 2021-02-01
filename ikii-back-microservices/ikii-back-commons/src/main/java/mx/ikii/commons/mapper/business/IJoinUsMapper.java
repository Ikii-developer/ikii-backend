package mx.ikii.commons.mapper.business;

import org.mapstruct.Mapper;

import mx.ikii.commons.mapper.utils.GenericMapper;
import mx.ikii.commons.mapper.utils.StringObjectIdMapper;
import mx.ikii.commons.payload.request.general.JoinUsRequest;
import mx.ikii.commons.payload.response.general.JoinUsResponse;
import mx.ikii.commons.persistence.collection.JoinUs;

@Mapper(componentModel = "spring", uses = { StringObjectIdMapper.class })
public interface IJoinUsMapper  extends GenericMapper<JoinUs, JoinUsRequest, JoinUsResponse> {

}
