package mx.ikii.customers.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import mx.ikii.commons.persistence.collection.SupportTicket;

@Repository
public interface ISupportTicketRepository extends MongoRepository<SupportTicket, String> {

}
