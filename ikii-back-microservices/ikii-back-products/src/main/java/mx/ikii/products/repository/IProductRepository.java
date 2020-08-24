package mx.ikii.products.repository;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import mx.ikii.commons.persistence.collection.Product;

@Repository
public interface IProductRepository extends MongoRepository<Product, String> {

	Optional<Product> findByName(String name);
	
	List<Product> findAllByBussinessId(Pageable pageable, ObjectId bussinessId);

}
