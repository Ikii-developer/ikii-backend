package mx.ikii.commons.mapper.support;

import org.mapstruct.Mapper;
import mx.ikii.commons.mapper.utils.GenericMapper;
import mx.ikii.commons.mapper.utils.StringObjectIdMapper;
import mx.ikii.commons.payload.request.support.SupportTicketRequest;
import mx.ikii.commons.payload.response.support.SupportTicketResponse;
import mx.ikii.commons.persistence.collection.SupportTicket;

@Mapper(componentModel = "spring", uses = { StringObjectIdMapper.class})
public interface ISupportTicketMapper extends GenericMapper<SupportTicket, SupportTicketRequest, SupportTicketResponse>{

}
