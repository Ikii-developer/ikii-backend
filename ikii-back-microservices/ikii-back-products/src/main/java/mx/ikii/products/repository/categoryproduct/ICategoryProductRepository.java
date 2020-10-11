package mx.ikii.products.repository.categoryproduct;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import mx.ikii.commons.persistence.collection.CategoryProduct;

@Repository
public interface ICategoryProductRepository extends MongoRepository<CategoryProduct, String> {

	Optional<CategoryProduct> findByName(String name);

	Optional<List<CategoryProduct>> findByBusinessId(ObjectId businessId);

	Optional<List<CategoryProduct>> findByParentProductCategoryId(ObjectId parentProductCategoryId);

}
