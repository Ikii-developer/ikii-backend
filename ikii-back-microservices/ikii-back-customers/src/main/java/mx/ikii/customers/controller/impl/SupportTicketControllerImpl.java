package mx.ikii.customers.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import mx.ikii.commons.payload.request.support.SupportTicketRequest;
import mx.ikii.commons.payload.response.support.SupportTicketResponse;
import mx.ikii.customers.controller.ISupportTicketController;
import mx.ikii.customers.service.ISupportTicketServiceWrapper;

@Component
public class SupportTicketControllerImpl implements ISupportTicketController {

  @Autowired
  private ISupportTicketServiceWrapper supportTicketServiceWrapper;

  @Override
  public ResponseEntity<SupportTicketResponse> create(
      @RequestBody SupportTicketRequest supportTicketRequest) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(supportTicketServiceWrapper.create(supportTicketRequest));
  }

  @Override
  public ResponseEntity<SupportTicketResponse> update(@PathVariable String id, 
      @RequestBody SupportTicketRequest supportTicketRequest) {
    return ResponseEntity.status(HttpStatus.OK).body(supportTicketServiceWrapper.update(id, supportTicketRequest));
  }

  @Override
  public ResponseEntity<SupportTicketResponse> getById(@PathVariable String id) {
    return ResponseEntity.status(HttpStatus.OK).body(supportTicketServiceWrapper.findById(id));
  }

  @Override
  public ResponseEntity<SupportTicketRequest> delete(@PathVariable String id) {
    supportTicketServiceWrapper.delete(id);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @Override
  public ResponseEntity<Page<SupportTicketResponse>> findAll(Pageable pageable) {
    return ResponseEntity.status(HttpStatus.OK).body(supportTicketServiceWrapper.findAll(pageable));
  }

}
