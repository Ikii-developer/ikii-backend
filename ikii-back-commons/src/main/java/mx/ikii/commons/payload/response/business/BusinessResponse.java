package mx.ikii.commons.payload.response.business;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessResponse {
	private String id;
	private String name;
	private String image;
	private String categoryId;
	private String customerId;
	private String description;
	private String deliveryTime;
	private LocalDateTime closeTime;
	private Boolean isOpen;
	private String status;
	private boolean isFavorite;
	private String postalCode;

}
