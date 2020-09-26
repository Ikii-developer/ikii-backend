package mx.ikii.business.repository.custom;

import java.util.List;

import mx.ikii.commons.persistence.collection.Business;

public interface IBusinessRepositoryCustom {
	
	List<Business> filterByBusinessName(String name);

}
