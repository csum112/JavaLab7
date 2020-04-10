package Optional;

public class Optional {

    public static void main(String[] args) {
        int n = 30, m = 40, k = 5;
        int numberOfDumbPlayers = 1;
        int numberOfProPlayers = 1;
        TurnBasedGame game = new TurnBasedGame(n, m, k, numberOfDumbPlayers, numberOfProPlayers, 10);
        game.addHumanPlayer("Me");
        try {
            game.play();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
