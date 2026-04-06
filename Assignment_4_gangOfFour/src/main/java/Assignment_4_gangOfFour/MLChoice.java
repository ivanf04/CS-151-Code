package Assignment_4_gangOfFour;

/** 
 * Machine learning choice algorithm.
 * Uses MoveHistory to predict the human player's next move based on
 * stored sequence frequencies, then returns the counter-move.
 */
public class MLChoice implements ChoiceAlgorithm {

    // Stores learned move patterns and recent history
    private final MoveHistory memory;

    // Fallback random-choice algorithm used when no prediction can be made
    private final randomChoice fallback;

    /**
     * Constructs an MLChoice object using the default sequence length of 5.
     *
     * @param memory the shared move history object
     */
    public MLChoice(MoveHistory memory) {
        this.memory = memory;
        this.fallback = new randomChoice();
    }


    /**
     * Determines the computer player's next move.
     *
     * This method checks the current prefix from recent history, looks up how
     * often each possible next human move has followed that prefix, predicts
     * the most frequent next human move, and returns the counter-move that
     * beats it. If there is not enough data, it falls back to a random move.
     *
     * @return the move chosen by the computer player
     */
    @Override
    public Move determineMove() {

        if (!memory.hasFullPrefix()) {
            return fallback.determineMove();
        }

        String prefix = memory.getCurrentPrefix();

        int rock = memory.getFrequency(prefix + "R");
        int paper = memory.getFrequency(prefix + "P");
        int scissors = memory.getFrequency(prefix + "S");

        if (rock == 0 && paper == 0 && scissors == 0) {
            return fallback.determineMove();
        }

        Move predicted = Move.ROCK;
        int max = rock; // default set to rock

        if (paper > max) {
            predicted = Move.PAPER;
            max = paper;
        }

        if (scissors > max) {
            predicted = Move.SCISSORS;
        }

        return counterMove(predicted);
    }

    /**
     * Returns the move that beats the predicted human move.
     *
     * @param move the predicted move of the human player
     * @return the counter-move that defeats the predicted move
     * @throws IllegalArgumentException if the move is invalid
     */
    private Move counterMove(Move move) {
        switch (move) {
            case ROCK:
                return Move.PAPER;
            case PAPER:
                return Move.SCISSORS;
            case SCISSORS:
                return Move.ROCK;
            default:
                throw new IllegalArgumentException("Invalid move.");
        }
    }
}
