package mx.ikii.products.controller.productbusiness;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import mx.ikii.commons.payload.request.product.ProductBusinessRequest;
import mx.ikii.commons.payload.request.product.ProductFilter;
import mx.ikii.commons.payload.response.product.ProductBusinessResponse;
import mx.ikii.commons.payload.response.product.ProductCategorySubcategory;
import mx.ikii.commons.payload.response.product.ProductGroupingByBusiness;
import mx.ikii.products.service.productbusiness.IProductBusinessServiceWrapper;

@Component
public class ProductBusinessControllerImpl implements IProductBusinessController {

  @Autowired
  private IProductBusinessServiceWrapper productBusinessServiceWrapper;

  @Override
  public ResponseEntity<Page<ProductBusinessResponse>> getAll(Pageable pageable) {
    return ResponseEntity.ok(productBusinessServiceWrapper.findAll(pageable));
  }

  @Override
  public ResponseEntity<ProductBusinessResponse> getById(@PathVariable String id) {
    return ResponseEntity.ok(productBusinessServiceWrapper.findById(id));
  }

  @Override
  public ResponseEntity<ProductBusinessResponse> getByProductName(
      @PathVariable String productName) {
    return ResponseEntity.ok(productBusinessServiceWrapper.getByProductName(productName));
  }

  @Override
  public ResponseEntity<ProductBusinessResponse> create(
      @RequestBody ProductBusinessRequest productRequest) {
    return ResponseEntity.ok(productBusinessServiceWrapper.create(productRequest));
  }

  @Override
  public ResponseEntity<List<ProductBusinessResponse>> createBulk(
      @RequestBody List<ProductBusinessRequest> productRequest) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(productBusinessServiceWrapper.createBulk(productRequest));
  }

  @Override
  public ResponseEntity<ProductBusinessResponse> update(
      @RequestBody ProductBusinessRequest productRequest, @PathVariable String id) {
    return ResponseEntity.ok(productBusinessServiceWrapper.update(productRequest, id));
  }

  @Override
  public ResponseEntity<Void> delete(@PathVariable String id) {
    productBusinessServiceWrapper.delete(id);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @Override
  public ResponseEntity<List<ProductGroupingByBusiness>> filterProduct(Pageable pageable,
      @RequestParam(required = false) String customerId, @RequestBody ProductFilter productFilter) {
    return ResponseEntity
        .ok(productBusinessServiceWrapper.filterProduct(pageable, customerId, productFilter));
  }

  @Override
  public ResponseEntity<List<ProductCategorySubcategory>> filterProductsByBusinessId(
      @PathVariable String businessId) {
    return ResponseEntity.ok(productBusinessServiceWrapper.findProductByCategory(businessId));
  }

}
