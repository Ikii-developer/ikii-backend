package mx.ikii.commons.feignclient.service.impl;

import mx.ikii.commons.persistence.collection.Business;
import mx.ikii.commons.persistence.collection.BusinessCategory;

public interface IBusinessFeignService {

	Business getById(String id);

	BusinessCategory getByCategoryId(String categoryId);

}
