package mx.ikii.commons.feignclient.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import mx.ikii.commons.payload.request.customer.CustomerDetailsRequest;
import mx.ikii.commons.payload.response.customer.CustomerDetailsResponse;

@FeignClient(name = "customers")
public interface ICustomerDetailsFeignRepository {

  @PutMapping("customers/customer-details/{id}")
  ResponseEntity<CustomerDetailsResponse> update(@RequestBody CustomerDetailsRequest userRequest,
      @PathVariable("id") String id);

  @GetMapping("customers/customer-details/customers/{id}")
  ResponseEntity<CustomerDetailsResponse> findByCustomerIdId(@PathVariable("id") String id);

}
