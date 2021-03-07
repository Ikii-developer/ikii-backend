package mx.ikii.commons.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {

  ACCEPTED("ACCEPTED"), DECLINE("DECLINE"), FINISHED("FINISHED"), ERROR("ERROR"), REFUNDED(
      "REFUNDED"), ON_HOLD("ON_HOLD");

  private final String status;
}