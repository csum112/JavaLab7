package Optional;

import Compulsory.Board;
import Compulsory.Player;

import java.util.Queue;

/**
 *
 */
public abstract class CivilizedPlayer extends Player {
    private Queue<Player> turnQueue;

    public CivilizedPlayer(Board board, String name, Queue<Player> turnQueue) {
        super(board, name);
        this.turnQueue = turnQueue;
    }

    private void announceTurn() {
        System.out.println("It`s " + this.getName() + "`s turn");
    }

    /**
     * Added turn queue.
     * Acts as both Consumer and Producer.
     * Players actually wait their turn now.
     */
    @Override
    public void run() {
        while (!this.board.isOver())
            synchronized (turnQueue) {
                while (turnQueue.element() != this)
                    try {
                        turnQueue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                announceTurn();
                takeTurn();
                turnQueue.remove();
                turnQueue.add(this);
                turnQueue.notifyAll();
            }

    }
}
