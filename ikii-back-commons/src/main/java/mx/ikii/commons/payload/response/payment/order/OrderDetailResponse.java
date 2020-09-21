package mx.ikii.commons.payload.response.payment.order;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDetailResponse {

	private String id;
	private String businessId;
	private List<ProductDetailResponse> products;

}
