package mx.ikii.payment.controller.payment;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.ikii.payment.payload.dto.PaymentMethodDTO;
import mx.ikii.payment.payload.request.PaymentSourceRequest;
import mx.ikii.payment.payload.response.PaymentMethodResponse;

@RestController
@RequestMapping("/source-payment")
public interface IkiiPaymentController {

  @GetMapping("ikii-customers/{ikiiCustomerId}")
  ResponseEntity<List<PaymentMethodResponse>> getPaymentMethod(String ikiiCustomerId);

  @PostMapping("/ikii-customers/{ikiiCustomerId}")
  ResponseEntity<PaymentMethodResponse> createPaymentMethod(String ikiiCustomerId,
      PaymentMethodDTO paymentMethodDTO);

  @PutMapping("/ikii-customers/{ikiiCustomerId}")
  ResponseEntity<PaymentMethodResponse> updatePaymentMethod(String ikiiCustomerId,
      PaymentSourceRequest paymentSourceRequest);

  @DeleteMapping("/ikii-customers/{ikiiCustomerId}/sources/{paymentMethodId}")
  ResponseEntity<Void> deletePaymentMethod(String ikiiCustomerId, String paymentMethodId);

}
