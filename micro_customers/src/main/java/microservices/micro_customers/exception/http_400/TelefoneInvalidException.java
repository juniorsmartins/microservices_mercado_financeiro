package microservices.micro_customers.exception.http_400;

import java.io.Serial;

public final class TelefoneInvalidException extends PoorlyRequestFormulatedException {

    @Serial
    private static final long serialVersionUID = 1L;

    public TelefoneInvalidException(String telefone) {
        super("exception.request.format.invalid.telefone", telefone);
    }

}

