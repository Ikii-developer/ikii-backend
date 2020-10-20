package mx.ikii.payment.service.ikii;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import mx.ikii.commons.domain.PaymentType;
import mx.ikii.commons.persistence.collection.PaymentOrder;
import mx.ikii.commons.persistence.collection.util.ProductDetail;
import mx.ikii.payment.repository.IPaymentOrderRepository;
import mx.ikii.payment.util.constant.Constants;

@Service
public class PaymentOrderServiceImpl implements IPaymentOrderService {
	
	@Autowired
	private IPaymentOrderRepository paymentOrderRepository;

	@Override
	public PaymentOrder getById(String id) {
		return paymentOrderRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PaymentOrder not found with id "+id));
	}

	@Override
	public PaymentOrder save(PaymentOrder paymentOrder) {
	    if(paymentOrder.getPaymentType().equals(PaymentType.CASH)) paymentOrder.setAccounted(true);
		return paymentOrderRepository.insert(paymentOrder);
	}

	@Override
	public PaymentOrder update(PaymentOrder paymentOrder) {
		return paymentOrderRepository.save(paymentOrder);
	}
	
	@Override
	public void calculateTotals(PaymentOrder paymentOrder) {
		BigDecimal suTotal = paymentOrder.getDetail().getProducts().stream()
		        .map(ProductDetail::getTotalAmount)
		        .reduce(BigDecimal.ZERO, BigDecimal::add);
		paymentOrder.setSuTotal(suTotal);
		paymentOrder.setTax(suTotal.multiply(Constants.TAX_VAT));
		paymentOrder.setTotal(suTotal.add(paymentOrder.getTax()));
	}

}
