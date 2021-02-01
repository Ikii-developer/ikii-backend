package mx.ikii.commons.persistence.collection;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.ikii.commons.persistence.collection.util.ProductDetail;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "OrderDetail")
public class OrderDetail {

  @Id
  private String id;
  private ObjectId businessId;
  private List<ProductDetail> products;

}
