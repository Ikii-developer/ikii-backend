package mx.ikii.commons.exception.handler;

public class FeignException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  public static String MESSAGE = "An error response was gotten from [%s Feign client]: %s";

  public FeignException() {
    super();
  }

  public FeignException(String msg) {
    super(msg);
  }

  public FeignException(Throwable ex) {
    super(ex);
  }

  public FeignException(String msg, Throwable ex) {
    super(msg, ex);
  }
}
