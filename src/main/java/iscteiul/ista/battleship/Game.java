
package iscteiul.ista.battleship;
/**
 * Representa um jogo de Battleship.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um jogo de Battleship, permitindo efetuar disparos
 * e consultar os resultados do jogo.
 *
 * @author fba
 */
public class Game implements IGame {
    private IFleet fleet;
    private List<IPosition> shots;

    private Integer countInvalidShots;
    private Integer countRepeatedShots;
    private Integer countHits;
    private Integer countSinks;


    /**
     * Cria um novo jogo com a frota especificada.
     *
     * @param fleet frota utilizada no jogo
     */
    public Game(IFleet fleet) {
        shots = new ArrayList<>();
        countInvalidShots = 0;
        countRepeatedShots = 0;
        this.fleet = fleet;
    }

    /**
     * Efetua um disparo na posição especificada.
     * O disparo pode ser inválido, repetido ou atingir um navio.
     * Se um navio for afundado, esse navio é devolvido.
     *
     * @param pos posição onde é efetuado o disparo
     * @return o navio atingido e afundado, ou {@code null} caso contrário
     */
    @Override
    public IShip fire(IPosition pos) {
        if (!validShot(pos))
            countInvalidShots++;
        else { // valid shot!
            if (repeatedShot(pos))
                countRepeatedShots++;
            else {
                shots.add(pos);
                IShip s = fleet.shipAt(pos);
                if (s != null) {
                    s.shoot(pos);
                    countHits++;
                    if (!s.stillFloating()) {
                        countSinks++;
                        return s;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Devolve a lista de posições onde foram efetuados disparos.
     *
     * @return lista de posições dos disparos efetuados
     */
    @Override
    public List<IPosition> getShots() {
        return shots;
    }

    /**
     * Devolve o número de disparos repetidos efetuados.
     *
     * @return número de disparos repetidos
     */
    @Override
    public int getRepeatedShots() {
        return this.countRepeatedShots;
    }

    /**
     * Devolve o número de disparos inválidos efetuados.
     *
     * @return número de disparos inválidos
     */
    @Override
    public int getInvalidShots() {
        return this.countInvalidShots;
    }

    /**
     * Devolve o número de disparos que atingiram navios.
     *
     * @return número de disparos que atingiram navios
     */
    @Override
    public int getHits() {
        return this.countHits;
    }

    /**
     * Devolve o número de navios afundados.
     *
     * @return número de navios afundados
     */
    @Override
    public int getSunkShips() {
        return this.countSinks;
    }

    /**
     * Devolve o número de navios que permanecem a flutuar.
     *
     * @return número de navios restantes
     */
    @Override
    public int getRemainingShips() {
        List<IShip> floatingShips = fleet.getFloatingShips();
        return floatingShips.size();
    }

    /**
     * Verifica se a posição indicada corresponde a uma posição válida
     * no tabuleiro.
     *
     * @param pos posição a validar
     * @return {@code true} se a posição for válida, {@code false} caso contrário
     */
    private boolean validShot(IPosition pos) {
        return (pos.getRow() >= 0 && pos.getRow() <= Fleet.BOARD_SIZE && pos.getColumn() >= 0
                && pos.getColumn() <= Fleet.BOARD_SIZE);
    }

    /**
     * Verifica se já foi efetuado um disparo na posição indicada.
     *
     * @param pos posição a verificar
     * @return {@code true} se a posição já tiver sido utilizada,
     *         {@code false} caso contrário
     */
    private boolean repeatedShot(IPosition pos) {
        for (int i = 0; i < shots.size(); i++)
            if (shots.get(i).equals(pos))
                return true;
        return false;
    }


    /**
     * Imprime o tabuleiro utilizando o marcador indicado para representar
     * as posições fornecidas.
     *
     * @param positions posições que devem ser apresentadas no tabuleiro
     * @param marker marcador utilizado para representar as posições
     */
    public void printBoard(List<IPosition> positions, Character marker) {
        char[][] map = new char[Fleet.BOARD_SIZE][Fleet.BOARD_SIZE];

        for (int r = 0; r < Fleet.BOARD_SIZE; r++)
            for (int c = 0; c < Fleet.BOARD_SIZE; c++)
                map[r][c] = '.';

        for (IPosition pos : positions)
            map[pos.getRow()][pos.getColumn()] = marker;

        for (int row = 0; row < Fleet.BOARD_SIZE; row++) {
            for (int col = 0; col < Fleet.BOARD_SIZE; col++)
                System.out.print(map[row][col]);
            System.out.println();
        }

    }


    /**
     * Imprime o tabuleiro, identificando com {@code X} as posições
     * onde foram efetuados disparos válidos.
     */
    public void printValidShots() {
        printBoard(getShots(), 'X');
    }


    /**
     * Imprime o tabuleiro, identificando com {@code #} as posições
     * ocupadas pela frota.
     */
    public void printFleet() {
        List<IPosition> shipPositions = new ArrayList<IPosition>();

        for (IShip s : fleet.getShips())
            shipPositions.addAll(s.getPositions());

        printBoard(shipPositions, '#');
    }

}
