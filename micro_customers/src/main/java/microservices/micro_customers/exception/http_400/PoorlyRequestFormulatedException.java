package microservices.micro_customers.exception.http_400;

import lombok.Getter;

import java.io.Serial;

@Getter
public abstract sealed class PoorlyRequestFormulatedException extends RuntimeException permits CpfInvalidException {

  @Serial
  private static final long serialVersionUID = 1L;

  private final String valor;

  private final String messageKey;

  protected PoorlyRequestFormulatedException(String messageKey, final String valor) {
    super(messageKey);
    this.messageKey = messageKey;
    this.valor = valor;
  }

}

