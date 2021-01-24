package mx.ikii.business.controller.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import mx.ikii.business.controller.IHelpBusinessController;
import mx.ikii.commons.payload.request.general.JoinUsRequest;

@Component
public class HelpBusinessControllerImpl implements IHelpBusinessController {

  @Override
  public ResponseEntity<Void> joinUs(@RequestBody JoinUsRequest joinUsRequest) {
    
    return null;
  }

}
