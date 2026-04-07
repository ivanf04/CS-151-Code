package Assignment_4_gangOfFour;

/**
* Represents the possible moves in classic Rock-Paper-Scissors.
* Each move has an associated integer value used for user input.
*/
public enum Move {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    private final int value;

    /**
    * Creates a Move with its integer value.
    *
    * @param value the integer representing this move (1..3)
    */
    Move(int value) {
        this.value = value;
    }

    /**
    * Returns the integer value associated with this move.
    *
    * @return the move's integer value (1..3)
    */
    public int getValue() {
        return value;
    }

    /**
    * Converts an integer into a Move.
    *
    * @param input the integer representation of a move (1 = ROCK, 2 = PAPER, 3 = SCISSORS)
    * @return the corresponding Move if input is valid; null otherwise
    */
    public static Move fromInt(int input) {
        for (Move m : values()) {
            if (m.value == input) return m;
        }
        return null;
    }
}
