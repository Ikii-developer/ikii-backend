package mx.ikii.customers.repository.impl;

import java.util.List;

import mx.ikii.commons.persistence.collection.CustomerAdress;

public interface ICustomerAdressRepositoryCustom {
	
	List<CustomerAdress> nearByMe2(String latitude, String longitude, Double distance);
	
	List<CustomerAdress> nearByMe3(String latitude, String longitude, Double distance);
	
}
