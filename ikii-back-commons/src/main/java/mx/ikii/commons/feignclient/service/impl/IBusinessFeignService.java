package mx.ikii.commons.feignclient.service.impl;

import java.util.List;
import mx.ikii.commons.persistence.collection.Business;
import mx.ikii.commons.persistence.collection.BusinessCategory;

public interface IBusinessFeignService {

  Business getById(String id);

  BusinessCategory getByCategoryId(String categoryId);

  List<Business> getAll();


}
