/**
 *
 */
package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Classe abstrata que representa um navio genérico no jogo Batalha Naval.
 * Implementa a interface IShip e fornece as funcionalidades comuns a todos os tipos de navios.
 */
public abstract class Ship implements IShip {

    private static final String GALEAO = "galeao";
    private static final String FRAGATA = "fragata";
    private static final String NAU = "nau";
    private static final String CARAVELA = "caravela";
    private static final String BARCA = "barca";

    /**
     * Constrói e devolve uma instância de um navio específico com base no tipo fornecido.
     *
     * @param shipKind O tipo/categoria do navio em formato texto (ex: "galeao", "fragata").
     * @param bearing  A orientação do navio (norte, sul, este, oeste).
     * @param pos      A posição inicial de referência do navio no tabuleiro.
     * @return Uma instância da subclasse correspondente ao tipo de navio, ou null se o tipo for desconhecido.
     */
    static Ship buildShip(String shipKind, Compass bearing, Position pos) {
        Ship s;
        switch (shipKind) {
            case BARCA:
                s = new Barge(bearing, pos);
                break;
            case CARAVELA:
                s = new Caravel(bearing, pos);
                break;
            case NAU:
                s = new Carrack(bearing, pos);
                break;
            case FRAGATA:
                s = new Frigate(bearing, pos);
                break;
            case GALEAO:
                s = new Galleon(bearing, pos);
                break;
            default:
                s = null;
        }
        return s;
    }


    private String category;
    private Compass bearing;
    private IPosition pos;
    protected List<IPosition> positions;


    /**
     * Construtor principal para inicializar os atributos comuns de um navio.
     *
     * @param category A categoria ou tipo do navio (ex: fragata, nau).
     * @param bearing  A orientação que o navio vai ter no tabuleiro.
     * @param pos      A posição principal a partir da qual o navio será colocado.
     */
    public Ship(String category, Compass bearing, IPosition pos) {
        assert bearing != null;
        assert pos != null;

        this.category = category;
        this.bearing = bearing;
        this.pos = pos;
        positions = new ArrayList<>();
    }

    /**
     * Devolve a categoria à qual o navio pertence.
     *
     * @return O nome do tipo de navio.
     * @see IShip#getCategory()
     */
    @Override
    public String getCategory() {
        return category;
    }

    /**
     * Devolve a lista completa de posições que este navio ocupa no tabuleiro[cite: 2].
     *
     * @return Uma lista de objetos do tipo IPosition[cite: 2].
     */
    public List<IPosition> getPositions() {
        return positions;
    }

    /**
     * Devolve a posição inicial ou de referência deste navio.
     *
     * @return O objeto IPosition associado à criação do navio.
     * @see IShip#getPosition()
     */
    @Override
    public IPosition getPosition() {
        return pos;
    }

    /**
     * Devolve a orientação (ponto cardeal) em que o navio se encontra.
     *
     * @return Um objeto Compass representando a orientação do navio.
     * @see IShip#getBearing()
     */
    @Override
    public Compass getBearing() {
        return bearing;
    }

    /**
     * Verifica se o navio ainda está a flutuar. Um navio flutua se tiver pelo menos uma posição que não foi atingida.
     *
     * @return true se o navio ainda tiver partes intactas, false se estiver totalmente afundado.
     * @see IShip#stillFloating()
     */
    @Override
    public boolean stillFloating() {
        for (int i = 0; i < getSize(); i++)
            if (!getPositions().get(i).isHit())
                return true;
        return false;
    }

    /**
     * Calcula e devolve o valor de linha mais alto (menor valor numérico de linha) que o navio ocupa.
     *
     * @return O número inteiro correspondente à linha mais ao topo.
     * @see IShip#getTopMostPos()
     */
    @Override
    public int getTopMostPos() {
        int top = getPositions().get(0).getRow();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getRow() < top)
                top = getPositions().get(i).getRow();
        return top;
    }

    /**
     * Calcula e devolve o valor de linha mais baixo (maior valor numérico de linha) que o navio ocupa.
     *
     * @return O número inteiro correspondente à linha mais ao fundo.
     * @see IShip#getBottomMostPos()
     */
    @Override
    public int getBottomMostPos() {
        int bottom = getPositions().get(0).getRow();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getRow() > bottom)
                bottom = getPositions().get(i).getRow();
        return bottom;
    }

    /**
     * Calcula e devolve o valor de coluna mais à esquerda (menor valor numérico de coluna) que o navio ocupa.
     *
     * @return O número inteiro correspondente à coluna mais à esquerda.
     * @see IShip#getLeftMostPos()
     */
    @Override
    public int getLeftMostPos() {
        int left = getPositions().get(0).getColumn();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getColumn() < left)
                left = getPositions().get(i).getColumn();
        return left;
    }

    /**
     * Calcula e devolve o valor de coluna mais à direita (maior valor numérico de coluna) que o navio ocupa.
     *
     * @return O número inteiro correspondente à coluna mais à direita.
     * @see IShip#getRightMostPos()
     */
    @Override
    public int getRightMostPos() {
        int right = getPositions().get(0).getColumn();
        for (int i = 1; i < getSize(); i++)
            if (getPositions().get(i).getColumn() > right)
                right = getPositions().get(i).getColumn();
        return right;
    }

    /**
     * Verifica se o navio ocupa uma determinada coordenada no tabuleiro.
     *
     * @param pos A posição a ser verificada.
     * @return true se o navio estiver na referida posição, false caso contrário.
     * @see IShip#occupies(IPosition)
     */
    @Override
    public boolean occupies(IPosition pos) {
        assert pos != null;

        for (int i = 0; i < getSize(); i++)
            if (getPositions().get(i).equals(pos))
                return true;
        return false;
    }

    /**
     * Verifica se este navio está demasiado perto (em posições adjacentes) em relação a outro navio.
     *
     * @param other O outro navio a comparar.
     * @return true se estiver demasiado próximo, false caso haja distância de segurança.
     * @see IShip#tooCloseTo(IShip)
     */
    @Override
    public boolean tooCloseTo(IShip other) {
        assert other != null;

        Iterator<IPosition> otherPos = other.getPositions().iterator();
        while (otherPos.hasNext())
            if (tooCloseTo(otherPos.next()))
                return true;

        return false;
    }

    /**
     * Verifica se este navio está adjacente ou demasiado próximo de uma determinada posição.
     *
     * @param pos A posição a verificar.
     * @return true se alguma parte do navio estiver adjacente à posição dada, false caso contrário.
     * @see IShip#tooCloseTo(IPosition)
     */
    @Override
    public boolean tooCloseTo(IPosition pos) {
        for (int i = 0; i < this.getSize(); i++)
            if (getPositions().get(i).isAdjacentTo(pos))
                return true;
        return false;
    }


    /**
     * Aplica um disparo a uma das posições ocupadas pelo navio, marcando-a como atingida (hit).
     *
     * @param pos A posição onde ocorreu o disparo.
     * @see IShip#shoot(IPosition)
     */
    @Override
    public void shoot(IPosition pos) {
        assert pos != null;

        for (IPosition position : getPositions()) {
            if (position.equals(pos))
                position.shoot();
        }
    }

    /**
     * Devolve uma representação em formato String das características do navio.
     *
     * @return Uma string contendo a categoria, a orientação e a posição base do navio.
     */
    @Override
    public String toString() {
        return "[" + category + " " + bearing + " " + pos + "]";
    }

}
