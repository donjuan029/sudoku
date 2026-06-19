package br.com.dio.model;

import java.util.Collection;
import java.util.List;

import static br.com.dio.model.GameStatusEnum.COMPLETE;
import static br.com.dio.model.GameStatusEnum.INCOMPLETE;
import static br.com.dio.model.GameStatusEnum.NON_STARTED;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Board {

    private final List<List<Space>> spaces;

    public Board(final List<List<Space>> spaces) {
        this.spaces = spaces;
    }

    public List<List<Space>> getSpaces() {
        return spaces;
    }

    // -------------------------------------------------------------------------
    // Status
    // -------------------------------------------------------------------------

    public GameStatusEnum getStatus() {
        if (noUserInputYet()) {
            return NON_STARTED;
        }
        return hasEmptyCells() ? INCOMPLETE : COMPLETE;
    }

    /**
     * Verdadeiro quando nenhuma célula não-fixa foi preenchida ainda.
     */
    private boolean noUserInputYet() {
        return allCells().noneMatch(s -> !s.isFixed() && nonNull(s.getActual()));
    }

    /**
     * Verdadeiro quando ainda há células sem valor (fixas ou não).
     */
    private boolean hasEmptyCells() {
        return allCells().anyMatch(s -> isNull(s.getActual()));
    }

    // -------------------------------------------------------------------------
    // Validação
    // -------------------------------------------------------------------------

    public boolean hasErrors() {
        if (getStatus() == NON_STARTED) {
            return false;
        }
        return !getErrors().isEmpty();
    }

    /**
     * Retorna mensagens descritivas de cada violação encontrada.
     * Lista vazia indica ausência de erros.
     */
    public List<String> getErrors() {
        if (getStatus() == NON_STARTED) {
            return List.of();
        }
        return SudokuValidator.validate(spaces);
    }

    // -------------------------------------------------------------------------
    // Mutação
    // -------------------------------------------------------------------------

    public boolean changeValue(final int col, final int row, final int value) {
        var space = spaces.get(col).get(row);
        if (space.isFixed()) {
            return false;
        }
        space.setActual(value);
        return true;
    }

    public boolean clearValue(final int col, final int row) {
        var space = spaces.get(col).get(row);
        if (space.isFixed()) {
            return false;
        }
        space.clearSpace();
        return true;
    }

    public void reset() {
        allCells().forEach(Space::clearSpace);
    }

    // -------------------------------------------------------------------------
    // Conclusão
    // -------------------------------------------------------------------------

    public boolean gameIsFinished() {
        return getStatus() == COMPLETE && !hasErrors();
    }

    // -------------------------------------------------------------------------
    // Utilitário interno
    // -------------------------------------------------------------------------

    /**
     * Stream plano de todas as 81 células do board.
     * Centraliza o flatMap para evitar repetição.
     */
    private java.util.stream.Stream<Space> allCells() {
        return spaces.stream().flatMap(Collection::stream);
    }
}
