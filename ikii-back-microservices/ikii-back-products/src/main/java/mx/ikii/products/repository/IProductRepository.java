package mx.ikii.products.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import mx.ikii.commons.persistence.collection.Product;

@Repository
public interface IProductRepository extends MongoRepository<Product, String> {
	
	Product findByName(String name);

}
