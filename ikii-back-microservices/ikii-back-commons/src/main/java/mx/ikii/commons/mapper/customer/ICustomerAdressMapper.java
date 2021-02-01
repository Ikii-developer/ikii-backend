package mx.ikii.commons.mapper.customer;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import lombok.Builder.Default;
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

	
	@Named("locationToGeoJsonPoint")
    default GeoJsonPoint locationToGeoJsonPoint(LocationDTO locationDTO) {
		GeoJsonPoint geoJsonPoint = 
				new GeoJsonPoint(locationDTO.getLongitude(), locationDTO.getLatitude());
		return geoJsonPoint;
    }
	
	
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
