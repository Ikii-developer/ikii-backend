package mx.ikii.payment.repository;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import mx.ikii.commons.persistence.collection.PaymentOrder;

@Repository
public interface IPaymentOrderRepository extends MongoRepository<PaymentOrder, String> {
  
    List<PaymentOrder> findByCustomerId(ObjectId customerId);

}
