package mx.ikii.commons.mapper.customer;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import mx.ikii.commons.mapper.utils.GenericMapper;
import mx.ikii.commons.mapper.utils.StringObjectIdMapper;
import mx.ikii.commons.payload.dto.LocationDTO;
import mx.ikii.commons.payload.request.customer.CustomerAdressRequest;
import mx.ikii.commons.payload.response.customer.CustomerAdressResponse;
import mx.ikii.commons.persistence.collection.CustomerAdress;

/**
 * This interface helps to map the DTO User resource(Entity,Request,Response)
 */
@Mapper(componentModel = "spring", uses = { StringObjectIdMapper.class })
public interface ICustomerAdressMapper extends GenericMapper<CustomerAdress, CustomerAdressRequest, CustomerAdressResponse> {

	@Mappings({
		@Mapping(target = "customerId", source = "customerId"),
		@Mapping(target = "businessId", source = "businessId"),
		@Mapping(target = "isMain", source = "isMain"),
		@Mapping(target = "postalCode", source = "postalCode"),
		@Mapping(target = "street", source = "street"),
		@Mapping(target = "colony", source = "colony"),
		@Mapping(target = "city", source = "city"),
		@Mapping(target = "interiorNumber", source = "interiorNumber"),
		@Mapping(target = "exteriorNumber", source = "exteriorNumber"),
		@Mapping(target = "description", source = "description"),
		@Mapping(target = "isValidate", source = "isValidate"),
		@Mapping(target = "nickname", source = "nickname"),
		@Mapping(target = "isCurrent", source = "isCurrent"),
		@Mapping(source = "location", target = "location", qualifiedByName = "locationToGeoJsonPoint")
	})
	CustomerAdress requestToEntity(CustomerAdressRequest request);
	
	@Named("locationToGeoJsonPoint")
    default GeoJsonPoint locationToGeoJsonPoint(LocationDTO locationDTO) {
		GeoJsonPoint geoJsonPoint = 
				new GeoJsonPoint(locationDTO.getLongitude(), locationDTO.getLatitude());
		return geoJsonPoint;
    }
	
//	@Mappings({
//		@Mapping(target = "customerId", source = "customerId"),
//		@Mapping(target = "businessId", source = "businessId"),
//		@Mapping(target = "isMain", source = "isMain"),
//		@Mapping(target = "postalCode", source = "postalCode"),
//		@Mapping(target = "street", source = "street"),
//		@Mapping(target = "colony", source = "colony"),
//		@Mapping(target = "city", source = "city"),
//		@Mapping(target = "interiorNumber", source = "interiorNumber"),
//		@Mapping(target = "exteriorNumber", source = "exteriorNumber"),
//		@Mapping(target = "description", source = "description"),
//		@Mapping(target = "isValidate", source = "isValidate"),
//		@Mapping(target = "nickname", source = "nickname"),
//		@Mapping(target = "isCurrent", source = "isCurrent"),
//		@Mapping(target = "locationDTO", source = "location", qualifiedByName = "geoJsonPointToLocation")
//	})
//	CustomerAdressResponse entityToResponse(CustomerAdress entity);
//	
//	@Named("geoJsonPointToLocation")
//    default LocationDTO geoJsonPointToLocationDTO(GeoJsonPoint location) {
//		return new LocationDTO(location.getX(), location.getY());
//    }
	
	
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
		source.setLocation(target.getLocation());
		source.setIsCurrent(target.getIsCurrent());
	}
	
}
