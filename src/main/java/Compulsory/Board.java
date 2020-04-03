package Compulsory;

import java.util.HashSet;

/**
 * Clasa din cerinta. Deasemenea si genereaza tokenurile la initilizarea unui nou board.
 */
public class Board {
    private HashSet<Token> tokens;
    private int m;
    private int k;
    private boolean isOver;
    private Player winner;

    public Board(int n, int m, int k) {
        this.tokens = new HashSet<Token>();
        this.m = m;
        this.k = k;
        fillBoard(n - m);
    }

    /**
     *
     * @param numberOfJacks Numarul de tokenuri blank.
     */
    private void fillBoard(int numberOfJacks) {
        for (int i = 0; i < numberOfJacks; i++) {
            tokens.add(new Token());
        }

        for (int i = 1; i <= m; i++) {
            tokens.add(new Token(i));
        }
    }

    public boolean isOver() {
        return isOver;
    }

    synchronized
    public boolean isBoardEmpty() {
        return tokens.size() == 0;
    }

    public HashSet<Token> getTokens() {
        return tokens;
    }

    public int getM() {
        return m;
    }

    public int getK() {
        return k;
    }

    public Player getWinner() {
        return winner;
    }

    /**
     * Daca exista tokenul pe board, il ia playerul care a apelat metoda si o sterge din set.
     * @param token
     * @return
     */
    synchronized
    public Token takeToken(Token token) {
        if (tokens.contains(token)) {
            tokens.remove(token);
            return token;
        } else throw new Error("Token does not exist");
    }

    /**
     * Ia un token random daca mai exista
     * @return
     */
    synchronized
    public Token takeFirstToken() {
        if (isBoardEmpty())
            throw new Error("Board is empty");
        final Token token = this.tokens.iterator().next();
        tokens.remove(token);
        return token;
    }

    /**
     * Metoda apelata de un player atunci cand a castigat
     * @param player Playerul castigator
     */
    synchronized
    public void signalWin(Player player) {
        this.isOver = true;
        this.winner = player;
    }
}
