package https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * UnsupportedMathOperationException
 *
 * Exceção personalizada utilizada para indicar erros relacionados
 * a operações matemáticas inválidas, como:
 *
 * - Valores não numéricos
 * - Divisão por zero
 * - Raiz quadrada de número negativo
 *
 * Essa exceção permite que a API retorne mensagens claras e específicas
 * ao cliente.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsupportedMathOperationException extends RuntimeException {

    public UnsupportedMathOperationException(String message) {
        super(message);
    }

}
