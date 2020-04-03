package Compulsory;

import java.util.LinkedList;
import java.util.List;

public class Player implements Runnable {
    private Board board;
    private String name;
    private List<Token> tokens;
    private int totalScore;
    private int roundScore;

    public Player(Board board, String name) {
        this.board = board;
        this.name = name;
        this.tokens = new LinkedList<Token>();
        this.roundScore = 0;
        this.totalScore = 0;
    }

    public String getName() {
        return name;
    }

    /**
     * Ia un token din board
     */
    private void takeRandomToken() {
        final Token token = board.takeFirstToken();
        tokens.add(token);
    }

    /**
     * Evalueaza tokenurile sale. Daca are o solutie castigatoare anunta board-ul.
     * @return Daca a castigat sau nu
     */
    private boolean haveIWon() {
        final Evaluator evaluator = new Evaluator(board.getK(), board.getM(), this.tokens);
        final int score = evaluator.getMaxSequenceLength();
        roundScore = Math.max(roundScore, score);
        return roundScore == board.getK();
    }

    @Override
    public void run() {
        System.out.println(name + " started playing!");
        while (!board.isOver()) {

            if (!board.isBoardEmpty())
                this.takeRandomToken();
            else break;
            if (haveIWon())
                board.signalWin(this);
        }
    }
}
