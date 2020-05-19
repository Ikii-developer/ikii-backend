package mx.ikii.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import mx.ikii.commons.persistence.collection.TransactionClip;

/**
 * This interface interacts directly with the TransactionClip Collection of the
 * mongodb database
 * 
 * @author Arturo Isaac Velazqeuz Vargas
 *
 */
@Repository
public interface ITransactionClipRepository extends MongoRepository<TransactionClip, String> {

}
