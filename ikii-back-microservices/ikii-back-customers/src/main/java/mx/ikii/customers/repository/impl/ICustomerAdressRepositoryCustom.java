package mx.ikii.customers.repository.impl;

import java.util.List;

import mx.ikii.commons.persistence.collection.util.BusinessNearByMe;

public interface ICustomerAdressRepositoryCustom {
	
	List<BusinessNearByMe> nearByMe(Double latitude, Double longitude, Double maxDistance);

}
