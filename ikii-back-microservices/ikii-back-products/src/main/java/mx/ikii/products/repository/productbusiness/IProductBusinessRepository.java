package mx.ikii.products.repository.productbusiness;

import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import mx.ikii.commons.persistence.collection.ProductBusiness;

@Repository
public interface IProductBusinessRepository extends MongoRepository<ProductBusiness, String> {

	List<ProductBusiness> findByBusinessIdIn(List<ObjectId> ids);
	
	Optional<ProductBusiness> findByName(String name);
	
	Optional<ProductBusiness> findByBusinessId(ObjectId businessId);
	
	List<ProductBusiness> findAllByBusinessId(Pageable pageable, ObjectId bussinessId);
	
}
