package mx.ikii.commons.mapper.customer;

import org.mapstruct.Mapper;
import mx.ikii.commons.mapper.utils.GenericMapper;
import mx.ikii.commons.mapper.utils.StringObjectIdMapper;
import mx.ikii.commons.payload.request.customer.CustomerDetailsRequest;
import mx.ikii.commons.payload.response.customer.CustomerDetailsResponse;
import mx.ikii.commons.persistence.collection.CustomerDetails;

/**
 * This interface helps to map the DTO User resource(Entity,Request,Response)
 * 
 * @author Francisco Javier Mart�nez Arazo
 *
 */
@Mapper(componentModel = "spring", uses = { StringObjectIdMapper.class })
public interface ICustomerDetailsMapper extends GenericMapper<CustomerDetails, CustomerDetailsRequest, CustomerDetailsResponse> {

}
