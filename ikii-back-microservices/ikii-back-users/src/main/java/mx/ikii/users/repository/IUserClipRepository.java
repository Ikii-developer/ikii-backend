package mx.ikii.users.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import mx.ikii.commons.persistence.collection.UserClip;

/**
 * This interface interacts directly with the UserClip Collection of the mongodb
 * database
 * 
 * @author Arturo Isaac Velazqeuz Vargas
 *
 */
@Repository
public interface IUserClipRepository extends MongoRepository<UserClip, String> {
	
	UserClip findByUserName(String userName);

}
