package mx.ikii.business.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.ikii.commons.payload.request.business.BusinessRequest;
import mx.ikii.commons.payload.response.business.BusinessResponse;

public interface IBusinessServiceWrapper {
  BusinessResponse findById(String id);

  BusinessResponse getByBusinesName(String businessName);

  List<BusinessResponse> filterByBusinessName(String businessName);

  Page<BusinessResponse> findAll(Pageable pageable, String customerid);

  List<BusinessResponse> findAllFeign();

  BusinessResponse create(BusinessRequest businessRequest);

  List<BusinessResponse> create(List<BusinessRequest> businessRequest);

  BusinessResponse update(BusinessRequest businessRequest, String id);

  void delete(String id);

}
