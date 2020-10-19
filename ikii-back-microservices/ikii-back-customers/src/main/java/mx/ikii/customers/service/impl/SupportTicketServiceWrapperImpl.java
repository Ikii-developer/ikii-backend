package mx.ikii.customers.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import mx.ikii.commons.mapper.support.ISupportTicketMapper;
import mx.ikii.commons.payload.request.support.SupportTicketRequest;
import mx.ikii.commons.payload.response.support.SupportTicketResponse;
import mx.ikii.commons.persistence.collection.SupportTicket;
import mx.ikii.commons.utils.PageHelper;
import mx.ikii.customers.service.ISupportTicketService;
import mx.ikii.customers.service.ISupportTicketServiceWrapper;

@Service
public class SupportTicketServiceWrapperImpl implements ISupportTicketServiceWrapper {

  @Autowired
  private ISupportTicketService supportTicketService;

  @Autowired
  private ISupportTicketMapper supportTicketMapper;

  @Override
  public SupportTicketResponse create(SupportTicketRequest supportTicketRequest) {
    SupportTicket supportTicket =
        supportTicketService.create(supportTicketMapper.requestToEntity(supportTicketRequest));
    return supportTicketMapper.entityToResponse(supportTicket);
  }

  @Override
  public SupportTicketResponse update(String id, SupportTicketRequest supportTicketRequest) {
    
    SupportTicket supportTicket = supportTicketService.findById(id);
    supportTicket.setDetail(supportTicketRequest.getDetail());
    //supportTicket.setStatus(SupportTicketStatus.RESOLVED);
    return supportTicketMapper.entityToResponse(supportTicketService.update(supportTicket));
  }

  @Override
  public SupportTicketResponse findById(String id) {
    SupportTicket supportTicket = supportTicketService.findById(id);
    return supportTicketMapper.entityToResponse(supportTicket);
  }

  @Override
  public void delete(String id) {
    supportTicketService.delete(id);
  }

  @Override
  public Page<SupportTicketResponse> findAll(Pageable pageable) {
    Page<SupportTicket> supportTicket = supportTicketService.findAll(pageable);
    List<SupportTicketResponse> supportTicketResponse = supportTicketMapper.entityToResponse(supportTicket.getContent());
    return PageHelper.createPage(supportTicketResponse, pageable, supportTicket.getTotalElements());
  }



}
