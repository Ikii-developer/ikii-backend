package mx.ikii.customers.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mx.ikii.commons.payload.request.support.SupportTicketRequest;
import mx.ikii.commons.payload.response.support.SupportTicketResponse;

@RestController
@RequestMapping("/support-ticket")
public interface ISupportTicketController {
  
  @PostMapping
  ResponseEntity<SupportTicketResponse> create(SupportTicketRequest supportTicketRequest);
  
  @PutMapping("/{id}")
  ResponseEntity<SupportTicketResponse> update(String id, SupportTicketRequest supportTicketRequest);
  
  @GetMapping("/{id}")
  ResponseEntity<SupportTicketResponse> getById(String id);
  
  @GetMapping
  ResponseEntity<Page<SupportTicketResponse>> findAll(Pageable pageable);
  
  @DeleteMapping("/{id}")
  ResponseEntity<SupportTicketRequest> delete(String id);

}
