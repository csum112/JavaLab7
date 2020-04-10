package Optional;

import Compulsory.Board;
import Compulsory.Player;

import java.util.Queue;

/**
 * Keeps picking random pieces. No need for any overrides
 */
public class DumbPlayer extends CivilizedPlayer {
    public DumbPlayer(Board board, String name, Queue<Player> turnQueue) {
        super(board, name, turnQueue);
    }

    @Override
    protected void makeTurnDecision() {
        super.makeTurnDecision();
    }
}
