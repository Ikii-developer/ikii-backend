package mx.ikii.commons.mapper.user;

import org.mapstruct.Mapper;

import mx.ikii.commons.mapper.utils.GenericMapper;
import mx.ikii.commons.mapper.utils.StringObjectIdMapper;
import mx.ikii.commons.payload.request.user.CustomerRequest;
import mx.ikii.commons.payload.response.user.CustomerAuthResponse;
import mx.ikii.commons.payload.response.user.CustomerResponse;
import mx.ikii.commons.persistence.collection.Customer;

/**
 * This interface helps to map the DTO User resource(Entity,Request,Response)
 * 
 * @author Arturo Isaac Velázquez Vargas
 *
 */
@Mapper(componentModel = "spring", uses = { StringObjectIdMapper.class })
public interface ICustomerMapper extends GenericMapper<Customer, CustomerRequest, CustomerResponse> {

	CustomerRequest entityToRequest(Customer customer);

	CustomerResponse entityToResponse(Customer customer);

	CustomerAuthResponse entityToAuthResponse(Customer customer);
	
	
	Customer authResponseToentity(CustomerAuthResponse customer);

}
