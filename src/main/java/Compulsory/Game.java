package Compulsory;

import com.github.javafaker.Faker;

import java.util.LinkedList;
import java.util.List;


/**
 * Clasa ce reprezinta un joc. Creaza Board-ul si playeri.
 */
public class Game {
    private Board board;
    private List<Thread> pool;
    private Faker faker;

    public Game(int totalTokens, int maxNumber, int winCondition, int numberOfPlayers)
    {
        this.board = new Board(totalTokens, maxNumber, winCondition);
        this.faker = new Faker();
        this.pool = new LinkedList<>();
        loadThreadPool(numberOfPlayers);
    }

    /**
     * @return Some random player
     */
    private Player genPlayer()
    {
        final String name = faker.name().username();
        return new Player(this.board, name);
    }

    /**
     * Creates n threads and assings random players to them
     * @param numberOfPlayers
     */
    private void loadThreadPool(int numberOfPlayers)
    {
        for (int i = 0; i < numberOfPlayers; i++) {
            pool.add(new Thread(genPlayer()));
        }
    }

    private void startThreads()
    {
        for (Thread thread : pool) {
            thread.start();
        }
    }

    private void joinThreads() throws InterruptedException {
        for (Thread thread : pool) {
            thread.join();
        }
    }

    /**
     * Porneste jocul si afiseaza in consola cine a castigat
     * @throws InterruptedException
     */
    public void play() throws InterruptedException {
        startThreads();
        joinThreads();
        final Player winner = board.getWinner();
        if (winner == null)
            System.out.println("Nobody won");
        else
            System.out.println(winner.getName() + " won!");
    }
}
