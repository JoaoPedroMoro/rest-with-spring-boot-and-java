package https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.utils;

import https.github.com.JoaoPedroMoro.rest_with_spring_boot_and_java.exception.UnsupportedMathOperationException;

/**
 * NumberUtils
 *
 * Classe utilitária responsável por operações auxiliares relacionadas
 * à validação e conversão de valores numéricos.
 *
 * É utilizada principalmente pela camada de serviço para verificar se
 * os valores recebidos são válidos e convertê-los para o tipo Double.
 *
 * Esta classe não possui estado e seus métodos são estáticos.
 */
public class NumberUtils {

    public static Double toDouble(String value) {
        if (value == null || value.isBlank()) throw new UnsupportedMathOperationException("Please set a numeric value!");

        String normalized = value.replace(",", "."); // Formato BR -> US

        try {
            return Double.parseDouble(normalized);
        } catch (NumberFormatException e) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }

    }

}
