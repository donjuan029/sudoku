package br.com.dio.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Valida as regras reais do Sudoku:
 * - Sem duplicatas em cada linha
 * - Sem duplicatas em cada coluna
 * - Sem duplicatas em cada quadrante 3x3
 */
public class SudokuValidator {

    /**
     * Retorna lista de mensagens de erro encontradas no board.
     * Lista vazia significa que não há violações de regra.
     */
    public static List<String> validate(final List<List<Space>> spaces) {
        var errors = new ArrayList<String>();

        checkRows(spaces, errors);
        checkColumns(spaces, errors);
        checkQuadrants(spaces, errors);

        return errors;
    }

    private static void checkRows(final List<List<Space>> spaces, final List<String> errors) {
        // spaces[col][row] — iteramos por row fixando cada linha
        for (int row = 0; row < 9; row++) {
            var seen = new ArrayList<Integer>();
            for (int col = 0; col < 9; col++) {
                var value = spaces.get(col).get(row).getActual();
                if (value != null) {
                    if (seen.contains(value)) {
                        errors.add("Número %d duplicado na linha %d".formatted(value, row + 1));
                        break; // uma mensagem por linha é suficiente
                    }
                    seen.add(value);
                }
            }
        }
    }

    private static void checkColumns(final List<List<Space>> spaces, final List<String> errors) {
        // spaces[col] é uma coluna inteira
        for (int col = 0; col < 9; col++) {
            var seen = new ArrayList<Integer>();
            for (int row = 0; row < 9; row++) {
                var value = spaces.get(col).get(row).getActual();
                if (value != null) {
                    if (seen.contains(value)) {
                        errors.add("Número %d duplicado na coluna %d".formatted(value, col + 1));
                        break;
                    }
                    seen.add(value);
                }
            }
        }
    }

    private static void checkQuadrants(final List<List<Space>> spaces, final List<String> errors) {
        // 9 quadrantes 3x3: origem em (colStart, rowStart)
        for (int colStart = 0; colStart < 9; colStart += 3) {
            for (int rowStart = 0; rowStart < 9; rowStart += 3) {
                var seen = new ArrayList<Integer>();
                for (int col = colStart; col < colStart + 3; col++) {
                    for (int row = rowStart; row < rowStart + 3; row++) {
                        var value = spaces.get(col).get(row).getActual();
                        if (value != null) {
                            if (seen.contains(value)) {
                                errors.add("Número %d duplicado no quadrante (%d,%d)"
                                        .formatted(value, colStart / 3 + 1, rowStart / 3 + 1));
                                break;
                            }
                            seen.add(value);
                        }
                    }
                }
            }
        }
    }
}
