package mx.ikii.products.controller.productbusiness;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mx.ikii.commons.payload.request.product.ProductBusinessRequest;
import mx.ikii.commons.payload.request.product.ProductFilter;
import mx.ikii.commons.payload.response.product.ProductBusinessResponse;
import mx.ikii.commons.payload.response.product.ProductCategorySubcategory;
import mx.ikii.commons.payload.response.product.ProductGroupingByBusiness;

@RestController
@RequestMapping("/")
public interface IProductBusinessController {

  @GetMapping
  ResponseEntity<Page<ProductBusinessResponse>> getAll(Pageable pageable);

  @GetMapping("{id}")
  ResponseEntity<ProductBusinessResponse> getById(String id);

  @GetMapping("/name/{name}")
  ResponseEntity<ProductBusinessResponse> getByProductName(String name);

  @PostMapping
  ResponseEntity<ProductBusinessResponse> create(ProductBusinessRequest productRequest);

  @PostMapping("bulk")
  ResponseEntity<List<ProductBusinessResponse>> createBulk(
      List<ProductBusinessRequest> productRequest);

  @PutMapping("{id}")
  ResponseEntity<ProductBusinessResponse> update(ProductBusinessRequest productRequest, String id);

  @DeleteMapping("{id}")
  ResponseEntity<Void> delete(String id);

  @PostMapping("/filter")
  ResponseEntity<List<ProductGroupingByBusiness>> filterProduct(Pageable pageable,
      String customerId, ProductFilter productFilter);

  @GetMapping("/business/{businessId}/filter")
  ResponseEntity<ProductCategorySubcategory> findProductByCategory(String businessId);
  
}
