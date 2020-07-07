package mx.ikii.payment.controller.payment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import mx.ikii.payment.payload.dto.PaymentMethodDTO;
import mx.ikii.payment.payload.request.PaymentSourceRequest;
import mx.ikii.payment.payload.response.PaymentMethodResponse;
import mx.ikii.payment.service.payment.IKiiPaymentServiceWrapper;

@Component
public class IkiiPaymentControllerImpl implements IkiiPaymentController {
	
	@Autowired
	private IKiiPaymentServiceWrapper iKiiPaymentService;

	@Override
	public ResponseEntity<List<PaymentMethodResponse>> getPaymentMethod(@PathVariable String customerId) {
		return ResponseEntity.ok(iKiiPaymentService.getPaymentMethod(customerId));
	}

	@Override
	public ResponseEntity<PaymentMethodResponse> createPaymentMethod(@PathVariable String customerId,
			@RequestBody PaymentMethodDTO paymentMethodDTO) {
		return ResponseEntity.ok(iKiiPaymentService.createPaymentMethod(customerId, paymentMethodDTO));
	}

	@Override
	public ResponseEntity<PaymentMethodResponse> updatePaymentMethod(@PathVariable String customerId,
			@RequestBody PaymentSourceRequest paymentSourceRequest) {
		iKiiPaymentService.updatePaymentMethod(customerId, paymentSourceRequest);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<Void> deletePaymentMethod(@PathVariable String customerId, @PathVariable String paymentMethodId) {
		iKiiPaymentService.deletePaymentMethod(customerId, paymentMethodId);
		return ResponseEntity.ok().build();
	}

}
