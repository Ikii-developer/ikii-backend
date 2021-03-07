package mx.ikii.payment.methods.conekta.service.customer;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.conekta.Customer;
import lombok.extern.slf4j.Slf4j;
import mx.ikii.payment.methods.conekta.repository.customer.ICustomerConektaRepository;
import mx.ikii.payment.payload.request.CustomerConektaRequest;

/**
 * Clientes te permite crear suscripciones y guardar métodos de pago para usuarios que requieren
 * pagos automáticos.
 * 
 * https://developers.conekta.com/api?language=java#customer
 */
@Slf4j
@Service
public class CustomerConektaServiceImpl implements ICustomerConektaService {

  @Autowired
  private ICustomerConektaRepository customerConektaRepository;

  @Override
  public Customer createCurstomer(CustomerConektaRequest customerConektaRequest) {
    JSONObject jsonRequest = new JSONObject(customerConektaRequest);
    log.info("[CustomerConektaServiceImpl] - INIT create customer with JSON request [{}] ",
        jsonRequest);
    Customer customer =
        customerConektaRepository.createCustomer(jsonRequest);
    return customer;
  }

  @Override
  public Customer updateCurstomer(CustomerConektaRequest customerConektaRequest) {
    log.info("[CustomerConektaServiceImpl] - INIT update customer with request[{}] ",
        customerConektaRequest);
    Customer customer =
        customerConektaRepository.updateCustomer(new JSONObject(customerConektaRequest));
    return customer;
  }

  @Override
  public Customer findCurstomer(String customerId) {
    log.info("[CustomerConektaServiceImpl] - INIT find customer with customerConektaId[{}] ",
        customerId);
    Customer customer = customerConektaRepository.findCustomer(customerId);
    return customer;
  }

}
