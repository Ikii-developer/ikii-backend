package mx.ikii.payment.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import mx.ikii.commons.payload.request.payment.conekta.CustomerConektaWrapperRequest;
import mx.ikii.payment.payload.request.CustomerConektaRequest;
import mx.ikii.payment.service.customer.IKiiPaymentCustomerServiceWrapper;

@Component
public class IkiiPaymentCustomerControllerImpl implements IkiiPaymentCustomerController {

  @Autowired
  private IKiiPaymentCustomerServiceWrapper ikiiPaymentServiceWrapper;

  @Override
  public ResponseEntity<Object> createCustomerConekta(
      @RequestBody CustomerConektaWrapperRequest customerConektaRequest) {
    return ResponseEntity
        .ok(ikiiPaymentServiceWrapper.createCustomerConekta(customerConektaRequest));
  }

  @Override
  public ResponseEntity<Object> updateCustomerConekta(
      @RequestBody CustomerConektaRequest customerConektaRequest) {
    return ResponseEntity
        .ok(ikiiPaymentServiceWrapper.updateCustomerConekta(customerConektaRequest));
  }

  @Override
  public ResponseEntity<Object> getCustomerConekta(@PathVariable String ikiiCustomerId) {
    return ResponseEntity.ok(ikiiPaymentServiceWrapper.findCustomerConekta(ikiiCustomerId));
  }

}
