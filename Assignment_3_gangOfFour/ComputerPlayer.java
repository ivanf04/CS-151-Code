package Assignment_3_gangOfFour;

/**
 * A Player controlled by the computer.
 * The move is selected by a ChoiceAlgorithm.
 */
public class ComputerPlayer implements Player {

    private final String name;
    private final ChoiceAlgorithm randomChoice;

    /**
     * Constructs a ComputerPlayer with the specified name and algorithm.
     *
     * @param name the name of the computer player
     * @param choiceAlgorithm the move-selection algorithm to use
     */
    public ComputerPlayer(String name, ChoiceAlgrorithm choiceAlgorithm) {
        this.name = name;
        this.choiceAlgorithm = choiceAlgorithm;
    }

    /**
     * Returns the computer player's name.
     *
     * @return the computer player's name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Selects and returns a Move using the configured algorithm.
     *
     * @return the chosen Move
     */
    @Override
    public Move getMove() {
        return choiceAlgorithm.determineMove();
    }

    /**
     * Passes completed round data to the algorithm.
     *
     * @param humanMove the human player's move
     * @param computerMove the computer player's move
     */
    public void recordRound(Move humanMove, Move computerMove) {
        choiceAlgorithm.recordRound(humanMove, computerMove);
    }

    /**
     * Saves algorithm data at the end of the game.
     */
    public void saveData() {
        choiceAlgorithm.saveData();
    }
}
