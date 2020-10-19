package mx.ikii.customers.service.impl;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import mx.ikii.commons.domain.SupportTicketStatus;
import mx.ikii.commons.exception.handler.ResourceNotFoundException;
import mx.ikii.commons.persistence.collection.SupportTicket;
import mx.ikii.customers.repository.ISupportTicketRepository;
import mx.ikii.customers.service.ISupportTicketService;

@Service
public class SupportTicketServiceImpl implements ISupportTicketService {

  @Autowired
  private ISupportTicketRepository supportTicketRepository;

  @Override
  public SupportTicket create(SupportTicket supportTicket) {
    supportTicket.setCreatedAt(LocalDateTime.now());
    supportTicket.setStatus(SupportTicketStatus.ACTIVE);
    return supportTicketRepository.insert(supportTicket);
  }

  @Override
  public SupportTicket update(SupportTicket supportTicket) {
    return supportTicketRepository.save(supportTicket);
  }

  @Override
  public SupportTicket findById(String id) {
    return supportTicketRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(id, SupportTicket.class));
  }

  @Override
  public void delete(String id) {
    supportTicketRepository.deleteById(id);
  }

  @Override
  public Page<SupportTicket> findAll(Pageable pageable) {
    return supportTicketRepository.findAll(pageable);
  }



}
