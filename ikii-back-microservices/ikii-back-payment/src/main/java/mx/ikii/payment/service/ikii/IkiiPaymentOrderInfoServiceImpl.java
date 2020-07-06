package mx.ikii.payment.service.ikii;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.ikii.commons.exception.handler.ResourceNotFoundException;
import mx.ikii.commons.persistence.collection.PaymentOrderInfo;
import mx.ikii.payment.repository.IkiiPaymentOrderInfoRepository;

@Service
public class IkiiPaymentOrderInfoServiceImpl implements IkiiPaymentOrderInfoService {
	
	@Autowired
	private IkiiPaymentOrderInfoRepository ikiiPaymentOrderInfoRepository;

	@Override
	public PaymentOrderInfo create(PaymentOrderInfo paymentOrderInfo) {
		
		return ikiiPaymentOrderInfoRepository.insert(paymentOrderInfo);
	}

	@Override
	public PaymentOrderInfo findById(String paymentInfoId) {
		Optional<PaymentOrderInfo> paymentOrderInfo = ikiiPaymentOrderInfoRepository.findById(paymentInfoId);
		if(!paymentOrderInfo.isPresent()) {
			throw new ResourceNotFoundException(paymentInfoId, PaymentOrderInfo.class);
		}
		return paymentOrderInfo.get();
	}

	@Override
	public PaymentOrderInfo update(PaymentOrderInfo paymentOrderInfo) {
		Optional<PaymentOrderInfo> paymentOrderInfoValidate = 
				ikiiPaymentOrderInfoRepository.findById(paymentOrderInfo.getId());
		//Check if exist the payment
		if(!paymentOrderInfoValidate.isPresent()) {
			throw new ResourceNotFoundException(paymentOrderInfo.getId(), PaymentOrderInfo.class);
		}
		return ikiiPaymentOrderInfoRepository.save(paymentOrderInfo);
	}

	@Override
	public void delete(PaymentOrderInfo paymentOrderInfo) {
		
		ikiiPaymentOrderInfoRepository.delete(paymentOrderInfo);
	}

}
