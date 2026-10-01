package iscteiul.ista.battleship;
/**
 * Representa uma barca no jogo Batalha Naval.
 * <p>
 * Uma barca é um navio que ocupa uma única posição na grelha.
 */
public class Barge extends Ship {

    private static final Integer SIZE = 1;
    private static final String NAME = "Barca";

    /**
     * Cria uma nova barca com a orientação e a posição inicial especificadas.
     *
     * @param bearing orientação da barca
     * @param pos     posição inicial da barca
     */
    public Barge(Compass bearing, IPosition pos) {
        super(Barge.NAME, bearing, pos);
        getPositions().add(new Position(pos.getRow(), pos.getColumn()));
    }

    /**
     * Devolve o tamanho da barca.
     *
     * @return tamanho da barca
     */
    @Override
    public Integer getSize() {
        return SIZE;
    }
}