package Optional;

import Compulsory.Board;
import Compulsory.Player;
import Compulsory.Token;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * The human player class. Every move is chosen manually.
 */
public class HumanPlayer extends CivilizedPlayer {
    public HumanPlayer(Board board, String name, Queue<Player> turnQueue) {
        super(board, name, turnQueue);
    }

    private Token letHumanChoose() {
        final List<Token> availableTokens = new LinkedList<Token>(this.board.getTokens());
        printAvailableTokens(availableTokens);
        Scanner scanner = new Scanner(System.in);
        int choiseIndex = scanner.nextInt();
        return availableTokens.get(choiseIndex);
    }

    private void printAvailableTokens(List<Token> availableTokens) {
        System.out.print("\n");
        for (int index = 0; index < availableTokens.size(); index++) {
            System.out.print(index + ": " + availableTokens.get(index) + " ");
        }
        System.out.print("\n");
    }

    @Override
    protected void makeTurnDecision() {
        final Token token = letHumanChoose();
        board.takeToken(token);
        tokens.add(token);
    }
}
