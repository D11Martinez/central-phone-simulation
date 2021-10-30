package central.telephone.simulation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Username already exists")
public class InstanceAlreadyExistsException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public InstanceAlreadyExistsException(String msg) {
    super(msg);
  }
}
