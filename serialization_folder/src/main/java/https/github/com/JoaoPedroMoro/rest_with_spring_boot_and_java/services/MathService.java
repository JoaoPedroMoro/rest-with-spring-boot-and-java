package https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.services;

import org.springframework.stereotype.Service;

/**
 * MathService
 *
 * Esta classe contém as regras de negócio relacionadas às operações
 * matemáticas da aplicação.
 *
 * Ela é utilizada pelos controllers para executar cálculos como soma,
 * subtração, divisão, multiplicação, média e raiz quadrada.
 *
 * Centralizar a lógica nesta classe facilita manutenção, testes unitários
 * e reutilização do código.
 */
@Service
public class MathService {

    public Double sum(Double n1, Double n2) { return n1 + n2; }

    public Double subtraction(Double n1, Double n2) { return n1 - n2; }

    public Double multiplication(Double n1, Double n2) { return n1 * n2; }

    public Double division(Double n1, Double n2) {
        if (n2 == 0) throw new UnsupportedMathOperationException("Division by zero is not allowed!");
        return n1 / n2;
    }

    public Double mean(Double n1, Double n2) { return (n1 + n2) / 2; }

    public Double squareRoot(Double value) {
        if (value < 0) throw new UnsupportedMathOperationException("Please set a number greater than or equal 0." +
                "\nSquare root of negative numbers is not allowed!");
        return Math.sqrt(value);
    }

}
