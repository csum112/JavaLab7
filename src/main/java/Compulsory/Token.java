package Compulsory;

/**
 * Clasa ce reprezinta o piesa. Poate avea o valoare numerica sau poate fi blank (jack).
 */
public class Token {
    private int value;
    private boolean jack;

    Token(int value) {
        this.value = value;
        this.jack = false;
    }

    Token() {
        this.jack = true;
    }

    public int getValue() {
        if (!isJack())
            return value;
        else throw new Error("Tried to read an empty token");
    }

    /**
     * @return Returneaza daca este o piesa blank.
     */
    public boolean isJack() {
        return jack;
    }

    @Override
    public String toString() {
        if (this.isJack())
            return "K";
        return Integer.toString(this.getValue());
    }
}
