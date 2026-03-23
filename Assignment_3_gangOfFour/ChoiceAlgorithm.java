package Assignment_3_gangOfFour;

/**
 * Defines a choice algorithm for the computer player.
 */
public interface ChoiceAlgorithm {

    /**
     * Determines the computer player's next move.
     *
     * @return the move chosen by the algorithm
     */
    Move determineMove();

    /**
     * Records the completed round so learning-based algorithms
     * can update their internal data.
     *
     * @param humanMove the human player's move
     * @param computerMove the computer player's move
     */
    void recordRound(Move humanMove, Move computerMove);

    /**
     * Saves any learned data at the end of the game.
     * Random algorithms can leave this empty.
     */
    void saveData();
}
