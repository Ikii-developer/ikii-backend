package mx.ikii.commons.payload.request.customer;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is to define type customer and additional data
 * 
 * @author Francisco Javier Mart�nez Arazo
 *
 */
@Data
@Builder
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDetailsRequest {
  private String id;
  private String customerId;
  private String customerConektaId;
  private Boolean isValidAccount;
  private String image;
  private Integer type;
  private List<String> businessFavorites;
}
