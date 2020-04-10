package Optional;

import Compulsory.Game;
import Compulsory.Player;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Extensia clasei game, astfel incat sa permita turn-based rules si un daemon ce tine evidenta timpului, si eventual inchide jocul.
 */
public class TurnBasedGame extends Game {
    private Queue<Player> turnQueue;
    private TimerDaemon timerDaemon;
    private int numberOfProPlayers;
    private int numberOfDumbPlayers;

    public TurnBasedGame(int totalTokens, int maxNumber, int winCondition, int numberOfPlayers) {
        super(totalTokens, maxNumber, winCondition, numberOfPlayers);
        this.turnQueue = new LinkedList<>();
    }

    public TurnBasedGame(int totalTokens, int maxNumber, int winCondition, int numberOfDumbPlayers, int numberOfProPlayers, int timeLimit) {
        super(totalTokens, maxNumber, winCondition, numberOfDumbPlayers);
        this.turnQueue = new LinkedList<>();
        this.timerDaemon = new TimerDaemon(timeLimit, board);
        this.numberOfProPlayers = numberOfProPlayers;
        this.numberOfDumbPlayers = numberOfDumbPlayers;
    }

    protected Player genDumbPlayer() {
        final CivilizedPlayer newPlayer = new DumbPlayer(this.board, this.faker.name().username(), turnQueue);
        this.turnQueue.add(newPlayer);
        return newPlayer;
    }

    protected Player genProPlayer() {
        final CivilizedPlayer newPlayer = new ProPlayer(this.board, this.faker.name().username(), turnQueue);
        this.turnQueue.add(newPlayer);
        return newPlayer;
    }

    @Override
    protected void startThreads() {
        super.startThreads();
        if (this.timerDaemon != null)
            this.timerDaemon.start();
    }

    /**
     * Functia ce incarca automat threadurile cu numarul de playeri
     * @param numberOfDumbPlayers
     * @param numberOfProPlayers
     */
    protected void loadThreadPool(int numberOfDumbPlayers, int numberOfProPlayers) {
        for (int i = 0; i < numberOfDumbPlayers; i++) {
            pool.add(new Thread(genDumbPlayer()));
        }
        for (int i = 0; i < numberOfProPlayers; i++) {
            pool.add(new Thread(genProPlayer()));
        }
    }

    /**
     * Functia ce adauga in pool un player manual
     * @param name
     */
    public void addHumanPlayer(String name) {
        final HumanPlayer player = new HumanPlayer(this.board, name, turnQueue);
        pool.add(new Thread(player));
        this.turnQueue.add(player);
    }

    /**
     * Aceeasi functie ca in super, adaptata pentru 2 tipuri de boti
     * @throws InterruptedException
     */
    public void play() throws InterruptedException {
        loadThreadPool(numberOfDumbPlayers, numberOfProPlayers);
        startThreads();
        joinThreads();
        final Player winner = board.getWinner();
        if (winner == null)
            System.out.println("Nobody won");
        else
            System.out.println(winner.getName() + " won!");
    }
}
