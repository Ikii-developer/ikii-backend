package mx.ikii.products.controller.product;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.ikii.commons.payload.request.product.ProductFilter;
import mx.ikii.commons.payload.response.product.ProductModelResponse;

@RestController
@RequestMapping("/model")
public interface IProductController {

	@PostMapping("/keyword")
	ResponseEntity<List<ProductModelResponse>> filterProduct(ProductFilter productFilter);
	
}
