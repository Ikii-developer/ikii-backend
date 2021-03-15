package mx.ikii.commons.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {

  ACCEPTED, DECLINE, FINISHED, ERROR, REFUNDED, ON_HOLD;

}
