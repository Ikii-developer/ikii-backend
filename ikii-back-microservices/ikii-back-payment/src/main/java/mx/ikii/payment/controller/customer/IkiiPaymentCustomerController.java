package mx.ikii.payment.controller.customer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mx.ikii.commons.payload.request.payment.conekta.CustomerConektaWrapperRequest;
import mx.ikii.payment.payload.request.CustomerConektaRequest;

@RestController
@RequestMapping("/customer-payment")
public interface IkiiPaymentCustomerController {

  @PostMapping
  ResponseEntity<Object> createCustomerConekta(
      CustomerConektaWrapperRequest customerConektaRequest);

  @PutMapping("/ikii-customers/{ikiiCustomerId}")
  ResponseEntity<Object> updateCustomerConekta(CustomerConektaRequest customerConektaRequest);

  @GetMapping("/ikii-customers/{ikiiCustomerId}")
  ResponseEntity<Object> getCustomerConekta(String ikiiCustomerId);

}
