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
 * @author Francisco Javier Mart�nez Arazo
 *
 */
@Mapper(componentModel = "spring", uses = { StringObjectIdMapper.class })
public interface ICustomerAdressMapper extends GenericMapper<CustomerAdress, CustomerAdressRequest, CustomerAdressResponse> {

//	@Mappings({
//		@Mapping(target = "target.customerId", source = "source.customerId"),
//		@Mapping(target = "target.isMain", source = "issource.Main"),
//		@Mapping(target = "target.postalCode", source = "source.postalCode"),
//		@Mapping(target = "target.street", source = "source.street"),
//		@Mapping(target = "target.colony", source = "source.colony"),
//		@Mapping(target = "target.city", source = "source.city"),
//		@Mapping(target = "target.interiorNumber", source = "source.interiorNumber"),
//		@Mapping(target = "target.exteriorNumber", source = "source.exteriorNumber"),
//		@Mapping(target = "target.description", source = "source.description"),
//		@Mapping(target = "target.isValidate", source = "source.isValidate"),
//		@Mapping(target = "target.nickname", source = "source.nickname"),
//		@Mapping(target = "target.latitude", source = "source.latitude"),
//		@Mapping(target = "target.longitude", source = "source.longitude"),
//		@Mapping(target = "target.isCurrent", source = "source.isCurrent")
//	})
	default void updateEntity(CustomerAdress source, CustomerAdress target) {
		source.setCustomerId(target.getCustomerId());
		source.setIsMain(target.getIsMain());
		source.setPostalCode(target.getPostalCode());
		source.setStreet(target.getStreet());
		source.setColony(target.getColony());
		source.setCity(target.getCity());
		source.setInteriorNumber(target.getInteriorNumber());
		source.setExteriorNumber(target.getExteriorNumber());
		source.setDescription(target.getDescription());
		source.setIsValidate(target.getIsValidate());
		source.setNickname(target.getNickname());
		source.setLatitude(target.getLatitude());
		source.setLongitude(target.getLongitude());
		source.setIsCurrent(target.getIsCurrent());
	}
	
}
