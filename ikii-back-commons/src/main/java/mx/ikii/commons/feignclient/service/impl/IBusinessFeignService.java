package mx.ikii.commons.feignclient.service.impl;

import mx.ikii.commons.persistence.collection.BusinessCategory;

public interface IBusinessFeignService {

	BusinessCategory getByCategoryId(String categoryId);

}
