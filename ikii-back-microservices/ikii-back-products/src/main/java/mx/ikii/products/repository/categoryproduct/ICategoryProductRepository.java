package mx.ikii.products.repository.categoryproduct;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import mx.ikii.commons.persistence.collection.CategoryProduct;

public interface ICategoryProductRepository extends MongoRepository<CategoryProduct, String>{

	Optional<CategoryProduct> findByName(String name);
}
