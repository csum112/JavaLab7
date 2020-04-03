package Compulsory;

public class Compulsory {
    /**
     *Variabilele reprezinta parametrii din cerinta.
     */
    public static void main(String[] args) {
        int n = 450;
        int m = 400;
        int k = 20;
        int numberOfPlayers = 5;
        Game game = new Game(n, m, k, numberOfPlayers);
        try {
            game.play();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
