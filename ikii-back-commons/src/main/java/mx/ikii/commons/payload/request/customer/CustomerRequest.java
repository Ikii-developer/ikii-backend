package mx.ikii.commons.payload.request.customer;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class CustomerRequest implements Serializable {

  private static final long serialVersionUID = 3511156059169211894L;

  private String id;
  private String name;
  private String lastName;
  private String secondLastName;
  private String email;
  private String phoneNumber;
  private String password;
  private String birthday;
  private Boolean isEnabled;

}
