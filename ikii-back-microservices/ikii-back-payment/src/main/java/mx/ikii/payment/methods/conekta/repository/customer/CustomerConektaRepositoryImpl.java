package mx.ikii.payment.methods.conekta.repository.customer;

import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import io.conekta.Conekta;
import io.conekta.Customer;
import io.conekta.Error;
import io.conekta.ErrorList;
import lombok.extern.slf4j.Slf4j;
import mx.ikii.commons.exception.handler.ConektaRepositoryException;
import mx.ikii.commons.exception.handler.ResourceNotFoundException;

/**
 * Clientes te permite crear suscripciones y guardar métodos de pago para usuarios que requieren
 * pagos automáticos.
 */
@Slf4j
@Repository
public class CustomerConektaRepositoryImpl implements ICustomerConektaRepository {

  @Value("${conekta.api-key}")
  private String apiKey;

  @Value("${conekta.api-version}")
  private String apiVersion;

  @Override
  public Customer findCustomer(String customerId) {
    Conekta.setApiKey(apiKey);
    Customer customer;
    try {
      customer = Customer.find(customerId);
    } catch (Error | ErrorList e) {
      log.error(e.getMessage());
      throw new ConektaRepositoryException(e.getMessage());
    }
    if (!Optional.ofNullable(customer).isPresent()) {
      throw new ResourceNotFoundException(customerId);
    }
    return customer;
  }

  @Override
  public Customer createCustomer(JSONObject customerJSON) {
    Conekta.setApiKey(apiKey);

    Customer customer;
    try {
      customer = Customer.create(customerJSON);
    } catch (Error e) {
      throw new ConektaRepositoryException(e.getMessage());
    } catch (ErrorList e) {
      throw new ConektaRepositoryException(e.details.toString());
    }
    return customer;
  }

  @Override
  public Customer updateCustomer(JSONObject customerJSON) {
    Conekta.setApiKey(apiKey);

    Customer customer = findCustomer(customerJSON.getString("id"));

    try {

      customer.update(customerJSON);
    } catch (Error | ErrorList e) {
      log.error(e.getMessage());
      throw new ConektaRepositoryException(e.getMessage());
    }

    return customer;
  }

  @Override
  public void deleteCustomer(String customerId) {
    Conekta.setApiKey(apiKey);

    Customer customer = findCustomer(customerId);

    try {
      customer.delete();
    } catch (Error | ErrorList e) {
      log.error(e.getMessage());
      throw new ConektaRepositoryException(e.getMessage());
    }
  }

}

/**
 * CONEKTA ERRORS
 * 
 * https://developers.conekta.com/api#errors
 *
 * api_error: Error inesperado por parte de Conekta. Este error puede ser capturado con la clase
 * `ApiError` . authentication_error: La llave usado en esta petición era inválida or no tiene
 * permisos para ejecutar esta petición. Este error puede ser capturado con la clase
 * `AuthenticationError`. conlict_error: Un conflict de versiones ocurrió, lo más probable es que la
 * versión que pasaste en las encabezados de la petición era incorrecta. malformed_request_error: El
 * JSON del cuerpo de tu petición era inválida o la codificación de caracteres en tu petición es
 * incorrecta. Este error puede ser capturado con la clase `MalFormedRequestError` .
 * parameter_validation_error: Algun parámetro de tu petición falta o tiene valores inválidos,
 * consulta el atributo `details` en tu petición para ver cuales campos fueron inválidos y por qué.
 * Este error puede ser capturado con la clase `ParameterValidationError` .
 * precondition_required_error: Falta ejecutar algunos pasos antes de procesar esta llamada. Por
 * ejemplo, si estas creando un `order`, la suma de los `line_items` tiene que estar mayor a 0 antes
 * de cobrarle, consulta el atributo `details` en tu petición para ver cuales campos fueron
 * inválidos y por qué. processing_error: Un error rutinario durante el procesamiento de tarjetas,
 * ocurre cuando procesen tarjetas y los fondos no pueden ser cobrados. Este error puede ser
 * capturado con la clase `ProcessingError` . resource_not_found_error: El objecto sobre cual
 * quisieras ejecutar este petición no existe. Ocurre normalmente cuando las llaves de la petición
 * corresponden a otra cuenta o no tienen permisos para buscar este objecto. Este error puede ser
 * capturado con la clase `ResourceNotFoundError` .
 */
