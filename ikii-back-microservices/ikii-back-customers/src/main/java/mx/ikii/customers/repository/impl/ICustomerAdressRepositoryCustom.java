package mx.ikii.customers.repository.impl;

import java.util.List;

import mx.ikii.commons.persistence.collection.CustomerAdress;

public interface ICustomerAdressRepositoryCustom {
	
	List<CustomerAdress> nearByMe(Double latitude, Double longitude, Double maxDistance);

}
