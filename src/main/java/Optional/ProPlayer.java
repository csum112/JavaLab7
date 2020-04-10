package Optional;

import Compulsory.Board;
import Compulsory.Player;
import Compulsory.Token;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ProPlayer extends CivilizedPlayer {
    public ProPlayer(Board board, String name, Queue<Player> turnQueue) {
        super(board, name, turnQueue);
    }

    /**
     * Logica jucatorului
     */
    @Override
    protected void makeTurnDecision() {
        final List<Token> availableTokens = new LinkedList<>(this.board.getTokens());

        if(pickJacksIfAvailable(availableTokens))
            return;
        else
            pickSmallestToken(availableTokens);
    }

    /**
     * Daca exista, alege cartea neinscriptionata
     * @param availableTokens
     * @return
     */
    private boolean pickJacksIfAvailable(List<Token> availableTokens) {
        for (Token token : availableTokens) {
            if (token.isJack()) {
                board.takeToken(token);
                this.tokens.add(token);
                return true;
            }
        }
        return false;
    }

    /**
     * Alege cel mai mic token de pe tabla
     * @param availableTokens
     */
    private void pickSmallestToken(List<Token> availableTokens) {
        Token minToken = availableTokens.get(0);
        for (Token token : availableTokens) {
            if (token.getValue() < minToken.getValue())
                minToken = token;
        }
        board.takeToken(minToken);
        tokens.add(minToken);
    }
}