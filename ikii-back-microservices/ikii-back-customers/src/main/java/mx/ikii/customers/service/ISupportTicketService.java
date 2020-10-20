package mx.ikii.customers.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import mx.ikii.commons.persistence.collection.SupportTicket;

public interface ISupportTicketService {

  SupportTicket create(SupportTicket supportTicket);

  SupportTicket update(SupportTicket supportTicket);

  SupportTicket findById(String id);
  
  Page<SupportTicket> findAll(Pageable pageable);

  void delete(String id);

}
