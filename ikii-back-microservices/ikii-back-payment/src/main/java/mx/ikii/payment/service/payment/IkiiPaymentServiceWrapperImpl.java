package mx.ikii.payment.service.payment;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import io.conekta.PaymentSource;
import lombok.extern.slf4j.Slf4j;
import mx.ikii.commons.exception.handler.ConektaRepositoryException;
import mx.ikii.commons.exception.handler.FeignException;
import mx.ikii.commons.feignclient.service.impl.ICustomerDetailsFeignService;
import mx.ikii.commons.payload.request.payment.conekta.CustomerConektaWrapperRequest;
import mx.ikii.commons.payload.request.payment.conekta.CustomerConektaWrapperRequest.ConektaCardInfo;
import mx.ikii.commons.persistence.collection.CustomerDetails;
import mx.ikii.payment.mapper.PaymentMethodMapper;
import mx.ikii.payment.methods.conekta.service.payments.IPaymentsConektaService;
import mx.ikii.payment.payload.dto.PaymentMethodDTO;
import mx.ikii.payment.payload.request.PaymentSourceRequest;
import mx.ikii.payment.payload.response.PaymentMethodResponse;
import mx.ikii.payment.service.customer.IkiiPaymentCustomerServiceWrapperImpl;
import mx.ikii.payment.payload.response.CustomerConektaResponse;


@Slf4j
@Service
public class IkiiPaymentServiceWrapperImpl implements IKiiPaymentServiceWrapper {

  @Autowired
  private IPaymentsConektaService paymentsConektaService;

  @Autowired
  private IkiiPaymentCustomerServiceWrapperImpl ikiiPaymentCustomerServiceWrapperImpl;

  @Autowired
  private ICustomerDetailsFeignService customerDetailsFeignService;

  @Override
  public List<PaymentMethodResponse> getPaymentMethod(String ikiiCustomerId) {
    log.info(
        "[IkiiPaymentServiceWrapperImpl] - INIT get payment method with customerId [{}] ",
        ikiiCustomerId);
    List<PaymentSource> paymentsSource = null;
    try {
      CustomerDetails customerDetails =
          customerDetailsFeignService.findByCustomerId(ikiiCustomerId);
      String customerConektaId = customerDetails.getCustomerConektaId();
      if (Objects.isNull(customerConektaId)) {
        throw new ResponseStatusException(HttpStatus.CONFLICT,
            "Provider payment user has not been created");
      }

      paymentsSource = paymentsConektaService.find(customerConektaId);
    } catch (ConektaRepositoryException e) {
      log.error(e.getMessage(), e);
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
    }
    return PaymentMethodMapper.paymentSourceToPaymentMethodsResponse(paymentsSource);
  }

  @Override
  public PaymentMethodResponse createPaymentMethod(String ikiiCustomerId,
      PaymentMethodDTO paymentMethodRequest) {
    log.info(
        "[IkiiPaymentServiceWrapperImpl] - INIT create payment method with customerId [{}] and request [{}] ",
        ikiiCustomerId, paymentMethodRequest);

    try {
      CustomerDetails customerDetails =
          customerDetailsFeignService.findByCustomerId(ikiiCustomerId);
      String customerConektaId = customerDetails.getCustomerConektaId();
      PaymentSource paymentSource = null;
      if (Objects.isNull(customerConektaId)) {
        CustomerConektaResponse customerConektaResponse = ikiiPaymentCustomerServiceWrapperImpl
            .createCustomerConekta(CustomerConektaWrapperRequest.builder()
                .conektaCardInfo(ConektaCardInfo.builder()
                    .token(paymentMethodRequest.getToken_id())
                    .type(paymentMethodRequest.getType()).build())
                .ikiiCustomerId(ikiiCustomerId).ikiiCustomerDetailId(customerDetails.getId())
                .build());

        customerConektaId = customerConektaResponse.getId();
      }
      paymentSource = paymentsConektaService.create(customerConektaId, paymentMethodRequest);
      return PaymentMethodMapper.paymentSourceToPaymentMethodResponse(paymentSource);
    } catch (FeignException | ConektaRepositoryException e) {
      log.error(e.getMessage(), e);
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
    }
  }

  @Override
  public void updatePaymentMethod(String ikiiCustomerId,
      PaymentSourceRequest paymentSourceRequest) {
    log.info(
        "[IkiiPaymentServiceWrapperImpl] - INIT update payment method with customerId [{}] and request [{}] ",
        ikiiCustomerId, paymentSourceRequest);
    try {
      CustomerDetails customerDetails =
          customerDetailsFeignService.findByCustomerId(ikiiCustomerId);
      String customerConektaId = customerDetails.getCustomerConektaId();
      if (Objects.isNull(customerConektaId)) {
        throw new ResponseStatusException(HttpStatus.CONFLICT,
            "Provider payment user has not been created");
      }
      paymentsConektaService.update(customerConektaId, paymentSourceRequest);
    } catch (ConektaRepositoryException e) {
      log.error(e.getMessage(), e);
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
    }
  }

  @Override
  public void deletePaymentMethod(String ikiiCustomerId, String paymentMethodId) {
    try {
      log.info(
          "[IkiiPaymentServiceWrapperImpl] - INIT delete payment method with customerId [{}] and paymentSourceId [{}] ",
          ikiiCustomerId, paymentMethodId);
      CustomerDetails customerDetails =
          customerDetailsFeignService.findByCustomerId(ikiiCustomerId);
      String customerConektaId = customerDetails.getCustomerConektaId();
      if (Objects.isNull(customerConektaId)) {
        throw new ResponseStatusException(HttpStatus.CONFLICT,
            "Provider payment user has not been created");
      }
      paymentsConektaService.delete(customerConektaId, paymentMethodId);
    } catch (ConektaRepositoryException e) {
      log.error(e.getMessage(), e);
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
    }
  }

}
