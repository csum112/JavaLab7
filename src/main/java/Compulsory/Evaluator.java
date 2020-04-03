package Compulsory;

import java.util.HashSet;
import java.util.List;

/**
 * Clasa auxiliara pentru facilitarea calcului progresiei aritmetice maximale.
 */
public class Evaluator {
    private int targetSequenceLength;
    private int maxInt;
    private List<Token> tokens;
    private HashSet<Integer> set;
    private int numberOfJacks;

    public Evaluator(int targetSequenceLength, int maxInt, List<Token> tokens) {
        this.targetSequenceLength = targetSequenceLength;
        this.maxInt = maxInt;
        this.tokens = tokens;
        this.set = new HashSet<>();
        buildSet();
    }

    /**
     * Extrage datele utile algoritmului din tokenuri.
     */
    private void buildSet() {
        for (Token token : tokens) {
            if (token.isJack())
                numberOfJacks++;
            else
                set.add(token.getValue());
        }
    }

    /**
     * Calculeaza care este lungimea secventei pentru parametrii dati, luand in calcul tokenurile libere.
     * @param start a1 - termenul initial
     * @param jump r - ratia progresiei
     * @return Lungimea progresiei
     */
    private int getProgressionLength(int start, int jump) {
        int usedJacks = 0;
        for (int i = 0; i < targetSequenceLength; i++) {
            int target = start + i * jump;
            if (this.set.contains(target)) {
                continue;
            } else if (usedJacks >= numberOfJacks)
                return i;
            else usedJacks++;
        }
        return targetSequenceLength;
    }

    /**
     * Calculeaza care este progresia aritmetica de lungime maxima pentru tokeurile date.
     * @return Lungimea progresiei.
     */
    public int getMaxSequenceLength() {
        final int maxJump = maxInt;
        int maxProgression = 1;
        for (int jump = 1; jump <= maxJump; jump++)
            for (Integer start : set)
            {
                int score = getProgressionLength(start, jump);
                if(score == targetSequenceLength)
                    return targetSequenceLength;
                else maxProgression = Math.max(maxProgression, score);
            }
        return maxProgression;
    }
}