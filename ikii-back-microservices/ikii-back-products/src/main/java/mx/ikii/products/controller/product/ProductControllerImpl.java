package mx.ikii.products.controller.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import mx.ikii.commons.payload.request.product.ProductFilter;
import mx.ikii.commons.payload.response.product.ProductModelResponse;
import mx.ikii.products.service.product.IProductServiceWrapper;

@Component
public class ProductControllerImpl implements IProductController {

	@Autowired
	private IProductServiceWrapper productServiceWrapper;

	@Override
	public ResponseEntity<List<ProductModelResponse>> filterProduct(@RequestBody ProductFilter productFilter){
		return ResponseEntity.ok(productServiceWrapper.searchByKeywords(productFilter));
	}

}
