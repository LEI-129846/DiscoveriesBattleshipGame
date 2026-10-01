package iscteiul.ista.battleship;

/**
 *Representação da embarcação Carrack no jogo Batalha Naval dos Descobrimentos.
 */
public class Carrack extends Ship {
    private static final Integer SIZE = 3;
    private static final String NAME = "Nau";

    /**
     * Constrói uma nova instância do navio, e calcula as suas coordenadas no tabuleiro dependendo da orientação fornecida.
     *
     * @param bearing A orientação geográfica em que o navio será posicionado.
     * @param pos A instância de {@link IPosition} que representa o ponto de partida do navio.
     * @throws IllegalArgumentException se a direção (bearing) fornecida não for suportada pela lógica de posicionamento.
     */
    public Carrack(Compass bearing, IPosition pos) throws IllegalArgumentException {
        super(Carrack.NAME, bearing, pos);
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
                throw new IllegalArgumentException("ERROR! invalid bearing for the carrack");
        }
    }

    /**
     * Obtém a dimensão total em quadrículas que esta embarcação ocupa na grelha.
     *
     * @return devolve o número de posições ocupadas pelo navio, no caso do Carrack devolve 3.
     * @see battleship.Ship#getSize()
     */
    @Override
    public Integer getSize() {
        return Carrack.SIZE;
    }

}
