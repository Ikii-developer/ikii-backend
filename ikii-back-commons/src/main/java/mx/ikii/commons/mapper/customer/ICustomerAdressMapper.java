package mx.ikii.commons.mapper.customer;

import org.mapstruct.Mapper;

import mx.ikii.commons.mapper.utils.GenericMapper;
import mx.ikii.commons.mapper.utils.StringObjectIdMapper;
import mx.ikii.commons.payload.request.customer.CustomerAdressRequest;
import mx.ikii.commons.payload.response.customer.CustomerAdressResponse;
import mx.ikii.commons.persistence.collection.CustomerAdress;

/**
 * This interface helps to map the DTO User resource(Entity,Request,Response)
 * 
 * @author Francisco Javier Martínez Arazo
 *
 */
@Mapper(componentModel = "spring", uses = { StringObjectIdMapper.class })
public interface ICustomerAdressMapper extends GenericMapper<CustomerAdress, CustomerAdressRequest, CustomerAdressResponse> {

}
