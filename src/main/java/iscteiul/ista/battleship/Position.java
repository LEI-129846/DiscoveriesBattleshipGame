/**
 *
 */
package iscteiul.ista.battleship;

import java.util.Objects;

/**
 * Representa uma posição ou coordenada no tabuleiro do jogo Batalha Naval.
 * Mantém a informação da localização e o estado atual da célula.
 */
public class Position implements IPosition {
    private int row;
    private int column;
    private boolean isOccupied;
    private boolean isHit;

    /**
     * Construtor que inicializa uma nova posição no tabuleiro.
     * Por omissão, a posição é criada como não estando ocupada (false) nem atingida (false).
     *
     * @param row    O valor inteiro que representa a linha da posição.
     * @param column O valor inteiro que representa a coluna da posição.
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
        this.isOccupied = false;
        this.isHit = false;
    }

    /**
     * Devolve a linha em que esta posição se encontra.
     *
     * @return A linha da posição.
     * @see battleship.IPosition#getRow()
     */
    @Override
    public int getRow() {
        return row;
    }

    /**
     * Devolve a coluna em que esta posição se encontra.
     *
     * @return A coluna da posição.
     * @see battleship.IPosition#getColumn()
     */
    @Override
    public int getColumn() {
        return column;
    }

    /**
     * Calcula e devolve o código hash para esta posição.
     * O valor do hash é gerado com base nos atributos atuais da posição: a coluna, se já foi atingida, se está ocupada e a linha.
     *
     * @return O valor inteiro correspondente ao código hash deste objeto[cite: 1].
     */
    @Override
    public int hashCode() {
        return Objects.hash(column, isHit, isOccupied, row);
    }

    /**
     * Verifica se esta posição é igual a outro objeto fornecido.
     * A igualdade é baseada nos valores exatos da linha e da coluna.
     *
     * @param otherPosition O objeto a ser comparado com a posição atual.
     * @return true se tiverem a mesma linha e coluna, false caso contrário.
     * @see battleship.IPosition#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object otherPosition) {
        if (this == otherPosition)
            return true;
        if (otherPosition instanceof IPosition) {
            IPosition other = (IPosition) otherPosition;
            return (this.getRow() == other.getRow() && this.getColumn() == other.getColumn());
        } else {
            return false;
        }
    }

    /**
     * Verifica se uma outra posição está adjacente a esta (distância máxima de 1 na linha ou coluna).
     *
     * @param other A outra posição a ser verificada.
     * @return true se a posição fornecida for adjacente, false caso contrário.
     * @see battleship.IPosition#isAdjacentTo(battleship.IPosition)
     */
    @Override
    public boolean isAdjacentTo(IPosition other) {
        return (Math.abs(this.getRow() - other.getRow()) <= 1 && Math.abs(this.getColumn() - other.getColumn()) <= 1);
    }

    /**
     * Marca a posição atual como estando ocupada por um navio (altera isOccupied para true).
     *
     * @see battleship.IPosition#occupy()
     */
    @Override
    public void occupy() {
        isOccupied = true;
    }

    /**
     * Marca a posição atual como tendo sido atingida por um tiro (altera isHit para true).
     *
     * @see battleship.IPosition#shoot()
     */
    @Override
    public void shoot() {
        isHit = true;
    }

    /**
     * Verifica o estado de ocupação da posição.
     *
     * @return true se a posição possuir um navio, false caso contrário.
     * @see battleship.IPosition#isOccupied()
     */
    @Override
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * Verifica se a posição já foi atingida por um tiro.
     *
     * @return true se já foi disparada, false caso contrário.
     * @see battleship.IPosition#isHit()
     */
    @Override
    public boolean isHit() {
        return isHit;
    }

    /**
     * Devolve uma representação em texto (String) da posição atual, mostrando a sua Linha e Coluna.
     *
     * @return Uma string formatada indicando a linha e a coluna.
     */
    @Override
    public String toString() {
        return ("Linha = " + row + " Coluna = " + column);
    }

}
