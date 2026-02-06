package https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.controllers;

import https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.exception.UnsupportedMathOperationException;
import https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.services.MathService;
import https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.utils.NumberUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MathController
 *
 * Esta classe é responsável por expor os endpoints REST relacionados
 * às operações matemáticas da aplicação.
 *
 * Ela atua como a camada de entrada da API, recebendo requisições HTTP,
 * extraindo os parâmetros da URL e delegando o processamento das regras
 * de negócio para a camada de serviço (MathService).
 *
 * Nenhuma lógica matemática complexa deve ser implementada diretamente
 * nesta classe, mantendo o princípio de separação de responsabilidades.
 */
@RestController
@RequestMapping("/math")
public class MathController {

    private final MathService mathService;

    public MathController(MathService mathService) {
        this.mathService = mathService;
    }

    // Path Param
    // http://localhost:8080/math/sum/3/5
    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) {
        Double n1 = NumberUtils.toDouble(numberOne);
        Double n2 = NumberUtils.toDouble(numberTwo);

        return mathService.sum(n1, n2);
    }

    // http://localhost:8080/math/subtraction/3/5
    @RequestMapping("/subtraction/{numberOne}/{numberTwo}")
    public Double subtraction(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) {
        Double n1 = NumberUtils.toDouble(numberOne);
        Double n2 = NumberUtils.toDouble(numberTwo);

        return mathService.subtraction(n1, n2);
    }

    // http://localhost:8080/math/division/3/5
    @RequestMapping("/division/{numberOne}/{numberTwo}")
    public Double division(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) {
        Double n1 = NumberUtils.toDouble(numberOne);
        Double n2 = NumberUtils.toDouble(numberTwo);

        return mathService.division(n1, n2);
    }

    // http://localhost:8080/math/multiplication/3/5
    @RequestMapping("/multiplication/{numberOne}/{numberTwo}")
    public Double multiplication(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) {
        Double n1 = NumberUtils.toDouble(numberOne);
        Double n2 = NumberUtils.toDouble(numberTwo);

        return mathService.multiplication(n1, n2);
    }

    // http://localhost:8080/math/mean/3/5
    @RequestMapping("/mean/{numberOne}/{numberTwo}")
    public Double mean(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo") String numberTwo
    ) {
        Double n1 = NumberUtils.toDouble(numberOne);
        Double n2 = NumberUtils.toDouble(numberTwo);

        return mathService.mean(n1, n2);
    }

    // http://localhost:8080/math/square-root/3
    @RequestMapping("/square-root/{number}")
    public Double squareRoot(
            @PathVariable("number") String number
    ) {
        Double n = NumberUtils.toDouble(number);

        return mathService.squareRoot(n);
    }

}
