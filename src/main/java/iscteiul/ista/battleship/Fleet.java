package iscteiul.ista.battleship;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa a frota de um jogador no jogo Batalha Naval.
 * A frota é constituída por um conjunto de navios e é responsável por gerir
 * o posicionamento, estado e pesquisa das embarcações no tabuleiro.
 */
public class Fleet implements IFleet {
    /**
     * Imprime na consola a informação detalhada de todos os navios de uma dada lista.
     *
     * @param ships Lista de navios
     */
    static void printShips(List<IShip> ships) {
        for (IShip ship : ships)
            System.out.println(ship);
    }

    // -----------------------------------------------------

    private List<IShip> ships;

    /**
     * Inicializa uma nova frota vazia.
     */
    public Fleet() {
        ships = new ArrayList<>();
    }

    /**
     * Devolve a lista de todos os navios que compõem a frota.
     *
     * @return A lista atual de navios da frota.
     */
    @Override
    public List<IShip> getShips() {
        return ships;
    }

    /**
     * Adiciona um novo navio à frota, garantindo que as regras do jogo são respeitadas.
     * A adição só é permitida se o limite da frota não tiver sido excedido, se o navio
     * estiver dentro dos limites do tabuleiro e se não colidir com outros navios.
     *
     * @param s O navio a ser adicionado à frota.
     * @return devolve true se o navio foi adicionado com sucesso, false caso contrário.
     *
     * @see IFleet#addShip(IShip)
     */
    @Override
    public boolean addShip(IShip s) {
        boolean result = false;
        if ((ships.size() <= FLEET_SIZE) && (isInsideBoard(s)) && (!colisionRisk(s))) {
            ships.add(s);
            result = true;
        }
        return result;
    }

    /**
     * Procura todos os navios da frota que pertencem a uma categoria específica.
     *
     * @param category A categoria do navio a pesquisar
     * @return Devolve uma lista contendo os navios da categoria solicitada.
     *
     * @see IFleet#getShipsLike(java.lang.String)
     */
    @Override
    public List<IShip> getShipsLike(String category) {
        List<IShip> shipsLike = new ArrayList<>();
        for (IShip s : ships)
            if (s.getCategory().equals(category))
                shipsLike.add(s);

        return shipsLike;
    }

    /**
     * Devolve todos os navios da frota que ainda têm pelo menos uma posição intacta.
     * @return Devolve uma lista com os navios que ainda não foram totalmente afundados.
     *
     * @see IFleet#getFloatingShips()
     */
    @Override
    public List<IShip> getFloatingShips() {
        List<IShip> floatingShips = new ArrayList<>();
        for (IShip s : ships)
            if (s.stillFloating())
                floatingShips.add(s);

        return floatingShips;
    }

    /**
     * Verifica se existe algum navio da frota a ocupar uma coordenada específica.
     *
     * @param pos A posição (coordenada) do tabuleiro a verificar.
     * @return O navio que ocupa a posição fornecida, ou null se a posição estiver livre.
     *
     * @see IFleet#shipAt(IPosition)
     */
    @Override
    public IShip shipAt(IPosition pos) {
        for (int i = 0; i < ships.size(); i++)
            if (ships.get(i).occupies(pos))
                return ships.get(i);
        return null;
    }

    /**
     * Verifica se os limites físicos do navio se encontram estritamente dentro da grelha de jogo.
     *
     * @param s O navio cujas posições vão ser validadas.
     * @return devolve true se o navio couber inteiramente no tabuleiro, false caso contrário.
     */
    private boolean isInsideBoard(IShip s) {
        return (s.getLeftMostPos() >= 0 && s.getRightMostPos() <= BOARD_SIZE - 1 && s.getTopMostPos() >= 0
                && s.getBottomMostPos() <= BOARD_SIZE - 1);
    }

    /**
     * Verifica se o navio pretendido colide ou está demasiado perto de embarcações já colocadas.
     *
     * @param s O navio a ser avaliado para risco de colisão.
     * @return true se houver risco de colisão, false se o espaço estiver livre e válido.
     */
    private boolean colisionRisk(IShip s) {
        for (int i = 0; i < ships.size(); i++) {
            if (ships.get(i).tooCloseTo(s))
                return true;
        }
        return false;
    }

    /**
     * Apresenta na consola o estado da frota, discriminando os navios por categoria
     * e indicando quais ainda flutuam.
     */
    public void printStatus() {
        printAllShips();
        printFloatingShips();
        printShipsByCategory("Galeao");
        printShipsByCategory("Fragata");
        printShipsByCategory("Nau");
        printShipsByCategory("Caravela");
        printShipsByCategory("Barca");
    }

    /**
     * Imprime na consola todos os navios da frota correspondentes a uma categoria de interesse.
     *
     * @param category A categoria específica a ser apresentada.
     */
    public void printShipsByCategory(String category) {
        assert category != null;

        printShips(getShipsLike(category));
    }

    /**
     * Imprime na consola todos os navios da frota que ainda flutuam (que não foram afundados).
     */
    public void printFloatingShips() {
        printShips(getFloatingShips());
    }

    /**
     * Imprime na consola a lista completa de navios integrados na frota.
     */
    void printAllShips() {
        printShips(ships);
    }

}
