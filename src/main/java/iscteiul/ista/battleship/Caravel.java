package iscteiul.ista.battleship;
/**
 * Representa uma caravela no jogo Batalha Naval.
 * Uma caravela é um navio que ocupa duas posições consecutivas na grelha.
 * A sua posição depende da orientação: as orientações norte e sul colocam
 * as posições na vertical, enquanto as orientações este e oeste colocam
 * as posições na horizontal.
 */
public class Caravel extends Ship {

    private static final Integer SIZE = 2;
    private static final String NAME = "Caravela";

    /**
     * Cria uma nova caravela com a orientação e a posição inicial especificadas.
     * A caravela ocupa duas posições consecutivas de acordo com a sua orientação.
     *
     * @param bearing orientação da caravela
     * @param pos     posição inicial da caravela
     * @throws NullPointerException se a orientação for {@code null}
     * @throws IllegalArgumentException se a orientação for inválida
     */
    public Caravel(Compass bearing, IPosition pos)
            throws NullPointerException, IllegalArgumentException {

        super(Caravel.NAME, bearing, pos);

        if (bearing == null)
            throw new NullPointerException("ERROR! invalid bearing for the caravel");

        switch (bearing) {
            case NORTH:
            case SOUTH:
                for (int r = 0; r < SIZE; r++)
                    getPositions().add(
                            new Position(pos.getRow() + r, pos.getColumn())
                    );
                break;

            case EAST:
            case WEST:
                for (int c = 0; c < SIZE; c++)
                    getPositions().add(
                            new Position(pos.getRow(), pos.getColumn() + c)
                    );
                break;

            default:
                throw new IllegalArgumentException(
                        "ERROR! invalid bearing for the caravel"
                );
        }
    }

    /**
     * Devolve o tamanho da caravela.
     *
     * @return tamanho da caravela
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }
}