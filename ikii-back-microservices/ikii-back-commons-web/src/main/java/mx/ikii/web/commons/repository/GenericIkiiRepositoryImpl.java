package mx.ikii.web.commons.repository;

import java.util.Map;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * 1. The bucket must be public
 * 
 * 2. One user with "Programmatic access" and access Policies to insert, update and delete objects on s3 
 *      or with a Policy "AmazonS3FullAccess"
 * 
 * 3. Folder
 * 
 * 4. 
 */
@Repository
public class GenericIkiiRepositoryImpl implements IGenericIkiiRepository {

  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public void updateDocumentFields(String id, Map<String, Object> fields, String collectionName) {

    Query query = new Query();
    query.addCriteria(Criteria.where("_id").is(new ObjectId(id)));

    Update update = new Update();

    // k = attribute, v = value
    fields.forEach((k, v) -> {
      update.set(k, v);
    });

    mongoTemplate.updateFirst(query, update, collectionName);

  }

}
