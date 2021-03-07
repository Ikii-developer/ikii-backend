package mx.ikii.payment.payload.request;

import java.time.Instant;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import mx.ikii.payment.payload.dto.PaymentMethodDTO;
import mx.ikii.payment.payload.dto.ShippingContactsDTO;

/**
 * https://developers.conekta.com/api?language=java#customer
 * 
 * <pre>
 * name: Nombre del cliente
 * phone: Teléfono del cliente (formato internacional) (opcional).
 * email: Correo electrónico del cliente.
 * plan_id: id secundario de algún plan (opcional).
 * 
 * payment_sources: Arreglo de las tarjetas que ya se tokenizaron 
 * 			o están por ser guardadas.
 * 			Ve a la sección de métodos de pago para más detalles (opcional).
 * 			https://developers.conekta.com/api?language=java#payment-source
 * 
 * shipping_contacts: Información de contacto el envío a cliente. 
 * 		Ve a la sección de Contactos de envío para más detalles
 * 		https://developers.conekta.com/api?language=java#shipping-contact
 * 
 * subscriptions: Subscripciones a las que puede estar asociado el cliente. 
 * 		Ve a la sección de Suscripciones para más detalles (opcional).
 * 		https://developers.conekta.com/api?language=java#subscription
 * </pre>
 */
@Data
@Builder
public class CustomerConektaRequest {

  // Identificador único asignado al azar.
  private String id;

  private String name;

  private String email;

  // Teléfono del cliente (formato internacional).
  private String phone;

  // Arreglo de las tarjetas que ya se tokenizaron o están por ser guardadas.
  private List<PaymentMethodDTO> payment_sources;

  // id secundario de algún plan.
  private String plan_id;

  /**
   * Información de contacto el envío a cliente.
   * https://developers.conekta.com/api?language=java#shipping-contact
   */
  private List<ShippingContactsDTO> shipping_contacts;

  /**
   * Relevant information on the activity of the company.
   */
  private AntiFraudInfo antifraud_info;

  /**
   * Subscripciones a las que puede estar asociado el cliente. Ve a la sección de Suscripciones para
   * más detalles.
   */
  // private Subscriptions subscriptions;

  @Builder
  @Data
  public static class AntiFraudInfo {

    /**
     * Date when the account was created. (optional if 'id' is sent)
     */
    private Long account_created_at;

    /**
     * Date of the customer's first successful purchase. (optional if 'id' is sent)
     */
    private Long first_paid_at;
  }

}
