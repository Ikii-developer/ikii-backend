package mx.ikii.business.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mx.ikii.commons.payload.request.general.JoinUsRequest;

@RestController
@RequestMapping("/")
public interface IHelpBusinessController {

  @PostMapping("join-us")
  ResponseEntity<Void> joinUs(JoinUsRequest joinUsRequest);
  
}
