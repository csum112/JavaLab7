package Optional;

import Compulsory.Board;

import java.util.concurrent.TimeUnit;

public class TimerDaemon extends Thread {
    private long elapsedSeconds;
    private long timeLimit;
    private Board board;

    public TimerDaemon(long timeLimit, Board board) {
        elapsedSeconds = 0;
        this.timeLimit = timeLimit;
        this.board = board;
        this.setDaemon(true);
    }

    public long getTime() {
        return elapsedSeconds;
    }

    private boolean isTimeLimitUp()
    {
        return elapsedSeconds >= timeLimit;
    }

    private void closeGame() throws InterruptedException {
        System.out.println("Time limit is up");
        synchronized (board)
        {
            board.signalWin(null);
            board.notifyAll();
        }
    }

    /**
     * Numara secundele, iar daca depasesc limita marcheaza boardul ca si finalizat.
     */
    @Override
    public void run() {
        while(!isTimeLimitUp())
        {
            try {
                TimeUnit.SECONDS.sleep(1);
                elapsedSeconds++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            closeGame();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
