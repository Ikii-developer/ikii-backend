package mx.ikii.customers.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import mx.ikii.commons.payload.request.support.SupportTicketRequest;
import mx.ikii.commons.payload.response.support.SupportTicketResponse;

public interface ISupportTicketServiceWrapper {

  SupportTicketResponse create(SupportTicketRequest supportTicket);

  SupportTicketResponse update(String id, SupportTicketRequest supportTicket);

  SupportTicketResponse findById(String id);

  void delete(String id);

  Page<SupportTicketResponse> findAll(Pageable pageable);

}
