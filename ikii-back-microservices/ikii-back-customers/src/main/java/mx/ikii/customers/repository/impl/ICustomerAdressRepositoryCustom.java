package mx.ikii.customers.repository.impl;

import java.util.List;

import org.springframework.data.geo.GeoResult;

import mx.ikii.commons.persistence.collection.CustomerAdress;

public interface ICustomerAdressRepositoryCustom {
	
	List<CustomerAdress> nearByMe(Double latitude, Double longitude, Double maxDistance);

	List<GeoResult<CustomerAdress>> nearByMe2(Double latitude, Double longitude, Double maxDistance);
	
}
