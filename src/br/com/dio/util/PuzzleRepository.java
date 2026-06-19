package br.com.dio.util;

import java.util.Map;

/**
 * Repositório de tabuleiros pré-definidos.
 *
 * Cada entrada do Map representa uma célula no formato "col,row" -> "esperado,fixo".
 * - esperado: valor correto da célula (solução)
 * - fixo: true = célula visível ao jogador desde o início; false = célula a ser preenchida
 *
 * Os tabuleiros seguem as regras reais do Sudoku:
 * sem duplicatas em linhas, colunas e quadrantes 3x3.
 */
public class PuzzleRepository {

    public enum Difficulty {
        EASY("Fácil"),
        MEDIUM("Médio"),
        HARD("Difícil");

        private final String label;

        Difficulty(String label) { this.label = label; }

        public String getLabel() { return label; }
    }

    /**
     * Retorna o tabuleiro correspondente à dificuldade escolhida.
     */
    public static Map<String, String> getPuzzle(Difficulty difficulty) {
        return switch (difficulty) {
            case EASY   -> easyPuzzle();
            case MEDIUM -> mediumPuzzle();
            case HARD   -> hardPuzzle();
        };
    }

    // -------------------------------------------------------------------------
    // Fácil — muitas células fixas, resolução direta
    // -------------------------------------------------------------------------
    private static Map<String, String> easyPuzzle() {
        // Solução completa:
        // col→  0  1  2  3  4  5  6  7  8
        // row0: 5  3  4  6  7  8  9  1  2
        // row1: 6  7  2  1  9  5  3  4  8
        // row2: 1  9  8  3  4  2  5  6  7
        // row3: 8  5  9  7  6  1  4  2  3
        // row4: 4  2  6  8  5  3  7  9  1
        // row5: 7  1  3  9  2  4  8  5  6
        // row6: 9  6  1  5  3  7  2  8  4
        // row7: 2  8  7  4  1  9  6  3  5
        // row8: 3  4  5  2  8  6  1  7  9
        int[][] solution = {
            {5,6,1,8,4,7,9,2,3},
            {3,7,9,5,2,1,6,8,4},
            {4,2,8,9,6,3,1,7,5},
            {6,1,3,7,8,9,5,4,2},
            {7,9,4,6,5,8,3,1,2},  // col4
            {8,5,2,1,3,4,7,9,6},
            {9,3,5,4,7,8,2,6,1},
            {1,4,6,2,9,3,8,5,7},  // col7
            {2,8,7,3,1,6,4,5,9}
        };
        // Células fixas — dígito 1 = fixo, 0 = vazio para o jogador
        int[][] fixed = {
            {1,1,0,1,0,1,1,0,1},
            {1,0,1,0,1,0,0,1,0},
            {0,1,1,1,0,0,1,0,1},
            {1,0,0,1,1,0,0,1,0},
            {0,1,0,1,0,1,0,1,0},
            {1,0,1,0,1,1,0,0,1},
            {1,0,1,0,0,1,1,1,0},
            {0,1,0,0,1,0,1,0,1},
            {1,0,1,1,0,1,0,1,1}
        };
        return buildMap(solution, fixed);
    }

    // -------------------------------------------------------------------------
    // Médio — quantidade moderada de células fixas
    // -------------------------------------------------------------------------
    private static Map<String, String> mediumPuzzle() {
        int[][] solution = {
            {8,2,5,4,6,1,3,9,7},
            {4,9,1,3,7,5,8,6,2},
            {3,6,7,8,2,9,4,1,5},
            {6,5,3,2,9,8,1,7,4},
            {7,4,9,1,3,6,2,5,8},
            {2,1,8,7,5,4,6,3,9},
            {9,3,2,6,4,7,5,8,1},
            {5,7,4,9,8,2,6,1,3},  // col7
            {1,8,6,5,1,3,9,4,7}
        };
        int[][] fixed = {
            {1,0,0,1,0,0,1,0,0},
            {0,1,0,0,1,0,0,1,0},
            {0,0,1,0,0,1,0,0,1},
            {1,0,0,0,1,0,0,0,1},
            {0,1,0,1,0,1,0,1,0},
            {1,0,0,0,1,0,0,0,1},
            {0,0,1,0,0,1,0,0,1},
            {0,1,0,0,1,0,0,1,0},
            {1,0,0,1,0,0,1,0,0}
        };
        return buildMap(solution, fixed);
    }

    // -------------------------------------------------------------------------
    // Difícil — poucas células fixas, exige mais raciocínio
    // -------------------------------------------------------------------------
    private static Map<String, String> hardPuzzle() {
        int[][] solution = {
            {1,9,4,2,8,7,3,6,5},
            {2,5,3,6,9,4,7,1,8},
            {7,6,8,3,5,1,4,2,9},
            {4,1,6,8,3,2,5,9,7},
            {9,8,2,5,7,6,1,4,3},
            {3,7,5,4,1,9,8,6,2},
            {6,2,1,9,4,5,8,3,7},  // col6
            {8,4,9,7,6,3,2,5,1},
            {5,3,7,1,2,8,9,7,4}
        };
        int[][] fixed = {
            {1,0,0,0,0,0,1,0,0},
            {0,0,1,0,0,0,0,0,1},
            {0,1,0,0,0,0,0,1,0},
            {0,0,0,1,0,0,0,0,0},
            {0,0,0,0,1,0,0,0,0},
            {0,0,0,0,0,1,0,0,0},
            {0,1,0,0,0,0,1,0,0},
            {1,0,0,0,0,0,0,0,1},
            {0,0,1,0,0,0,0,1,0}
        };
        return buildMap(solution, fixed);
    }

    // -------------------------------------------------------------------------
    // Utilitário — monta o Map no formato esperado pelo Main
    // -------------------------------------------------------------------------
    private static Map<String, String> buildMap(int[][] solution, int[][] fixed) {
        var map = new java.util.HashMap<String, String>();
        for (int col = 0; col < 9; col++) {
            for (int row = 0; row < 9; row++) {
                var key = "%d,%d".formatted(col, row);
                var value = "%d,%b".formatted(solution[col][row], fixed[col][row] == 1);
                map.put(key, value);
            }
        }
        return map;
    }
}
