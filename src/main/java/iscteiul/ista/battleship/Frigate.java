package iscteiul.ista.battleship;
/**
 * Representa uma fragata no jogo Battleship.
 * Uma fragata ocupa quatro posições consecutivas no tabuleiro.
 */

public class Frigate extends Ship {
    private static final Integer SIZE = 4;
    private static final String NAME = "Fragata";

    /**
     * Cria uma nova fragata com a orientação e a posição inicial especificadas.
     *
     * @param bearing orientação em que a fragata é colocada
     * @param pos posição inicial da fragata
     * @throws IllegalArgumentException se a orientação especificada for inválida
     */
    public Frigate(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Frigate.NAME, bearing, pos);
        switch (bearing) {
            case NORTH:
            case SOUTH:
                for (int r = 0; r < SIZE; r++)
                    getPositions().add(new Position(pos.getRow() + r, pos.getColumn()));
                break;
            case EAST:
            case WEST:
                for (int c = 0; c < SIZE; c++)
                    getPositions().add(new Position(pos.getRow(), pos.getColumn() + c));
                break;
            default:
                throw new IllegalArgumentException("ERROR! invalid bearing for thr frigate");
        }
    }

    /**
     * Devolve o tamanho da fragata.
     *
     * @return número de posições ocupadas pela fragata
     */
    @Override
    public Integer getSize() {
        return Frigate.SIZE;
    }

}