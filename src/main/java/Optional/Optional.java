package Optional;

public class Optional {

    public static void main(String[] args) {
        int n = 800, m = 800, k = 700;
        int numberOfDumbPlayers = 2;
        int numberOfProPlayers = 2;
        TurnBasedGame game = new TurnBasedGame(n, m, k, numberOfDumbPlayers, numberOfProPlayers, 10);
        game.addHumanPlayer("Me");
        try {
            game.play();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
