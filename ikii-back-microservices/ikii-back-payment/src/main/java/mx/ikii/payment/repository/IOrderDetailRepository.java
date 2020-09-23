package mx.ikii.payment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import mx.ikii.commons.persistence.collection.OrderDetail;

@Repository
public interface IOrderDetailRepository extends MongoRepository<OrderDetail, String> {

}
