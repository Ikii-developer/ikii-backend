package mx.ikii.commons.feignclient.repository;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import mx.ikii.commons.payload.response.business.BusinessCategoryResponse;
import mx.ikii.commons.payload.response.business.BusinessResponse;

@FeignClient(name = "business")
public interface IBusinessFeignRepository {

  @GetMapping("/business/categories/{id}")
  ResponseEntity<BusinessCategoryResponse> getByCategoryId(@PathVariable("id") String id);

  @GetMapping("/business/{id}")
  ResponseEntity<BusinessResponse> getById(@PathVariable("id") String id);

  @GetMapping("/business/feign")
  ResponseEntity<List<BusinessResponse>> getAllFegin();

}
