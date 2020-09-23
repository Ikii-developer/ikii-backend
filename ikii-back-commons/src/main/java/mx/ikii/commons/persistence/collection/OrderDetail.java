package mx.ikii.commons.persistence.collection;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import mx.ikii.commons.persistence.collection.util.ProductDetail;

@Data
@Document(collection = "OrderDetail")
public class OrderDetail {

	@Id
	private String id;
	private ObjectId businessId;
	private List<ProductDetail> products;

}
