package mx.ikii.payment.service.customer;

import java.time.Instant;
import java.util.Arrays;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import io.conekta.Customer;
import lombok.extern.slf4j.Slf4j;
import mx.ikii.commons.exception.handler.ConektaRepositoryException;
import mx.ikii.commons.exception.handler.FeignException;
import mx.ikii.commons.feignclient.service.impl.ICustomerDetailsFeignService;
import mx.ikii.commons.feignclient.service.impl.ICustomerFeignService;
import mx.ikii.commons.payload.request.customer.CustomerDetailsRequest;
import mx.ikii.commons.payload.request.payment.conekta.CustomerConektaWrapperRequest;
import mx.ikii.commons.persistence.collection.CustomerDetails;
import mx.ikii.payment.mapper.CustomerConektaMapper;
import mx.ikii.payment.methods.conekta.service.customer.ICustomerConektaService;
import mx.ikii.payment.payload.dto.PaymentMethodDTO;
import mx.ikii.payment.payload.request.CustomerConektaRequest;
import mx.ikii.payment.payload.request.CustomerConektaRequest.AntiFraudInfo;
import mx.ikii.payment.payload.response.CustomerConektaResponse;

@Service
@Slf4j
public class IkiiPaymentCustomerServiceWrapperImpl implements IKiiPaymentCustomerServiceWrapper {

  @Autowired
  private ICustomerConektaService customerConektaService;

  @Autowired
  private ICustomerDetailsFeignService customertDetailsFeignService;

  @Autowired
  private ICustomerFeignService customerFeignService;

  @Autowired
  private ICustomerDetailsFeignService customerDetailsFeignService;


  @Override
  public CustomerConektaResponse createCustomerConekta(
      CustomerConektaWrapperRequest conektaCustomerWrapperRequest) {
    log.info("[IkiiPaymentCustomerServiceWrapperImpl] - INIT create customer with request [{}] ",
        conektaCustomerWrapperRequest);

    String customerId = conektaCustomerWrapperRequest.getIkiiCustomerId();
    String customerDetailId = conektaCustomerWrapperRequest.getIkiiCustomerDetailId();
    Customer customer = null;
    mx.ikii.commons.persistence.collection.Customer ikiiCustomer =
        customerFeignService.getById(customerId);

    CustomerConektaRequest customerConektaRequest =
        createConektaRequest(conektaCustomerWrapperRequest, ikiiCustomer);

    customer = customerConektaService.createCurstomer(customerConektaRequest);
    customertDetailsFeignService.update(
        CustomerDetailsRequest.builder()
            .customerConektaId(customer.getId())
            .build(),
        customerDetailId);

    CustomerConektaResponse response =
        CustomerConektaMapper.customerConektaToCustomerConektaResponse(customer);
    return response;

  }

  private CustomerConektaRequest createConektaRequest(
      CustomerConektaWrapperRequest conektaCustomerWrapperRequest,
      mx.ikii.commons.persistence.collection.Customer ikiiCustomer) {
    CustomerConektaRequest customerConektaRequest =
        CustomerConektaRequest.builder()
            .name(ikiiCustomer.getName())
            .email(ikiiCustomer.getEmail())
            .phone(ikiiCustomer.getPhoneNumber())
            .payment_sources(Arrays.asList(PaymentMethodDTO.builder()
                .type(conektaCustomerWrapperRequest.getConektaCardInfo().getType())
                .token_id(conektaCustomerWrapperRequest.getConektaCardInfo().getToken())
                .build()))
            .antifraud_info(AntiFraudInfo.builder()
                .account_created_at(Instant.now().toEpochMilli())
                .first_paid_at(1485151007l)
                .build())
            .build();
    return customerConektaRequest;
  }

  @Override
  public CustomerConektaResponse updateCustomerConekta(
      CustomerConektaRequest customerConektaRequest) {
    Customer customer = customerConektaService.updateCurstomer(customerConektaRequest);
    CustomerConektaResponse response =
        CustomerConektaMapper.customerConektaToCustomerConektaResponse(customer);
    return response;
  }

  @Override
  public CustomerConektaResponse findCustomerConekta(String ikiiCustomerId) {
    log.info(
        "[IkiiPaymentCustomerServiceWrapperImpl] - INIT get customer with ikiiCustomerId [{}] ",
        ikiiCustomerId);
    try {
      CustomerDetails customerDetails =
          customerDetailsFeignService.findByCustomerId(ikiiCustomerId);
      String customerConektaId = customerDetails.getCustomerConektaId();
      if (Objects.isNull(customerConektaId)) {
        throw new ResponseStatusException(HttpStatus.CONFLICT,
            "Provider payment user has not been created");
      }
      Customer customer = customerConektaService.findCurstomer(customerConektaId);
      CustomerConektaResponse response =
          CustomerConektaMapper.customerConektaToCustomerConektaResponse(customer);
      return response;
    } catch (FeignException | ConektaRepositoryException e) {
      log.error(e.getMessage(), e);
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
    }
  }



}
