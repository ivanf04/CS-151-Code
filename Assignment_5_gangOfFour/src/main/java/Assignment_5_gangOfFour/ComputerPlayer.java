package Assignment_5_gangOfFour;

/**
 * A Player controlled by the computer.
 * The move is selected by a ChoiceAlgorithm.
 */
public class ComputerPlayer implements Player {

    private final String name;
    private final ChoiceAlgorithm choiceAlgorithm;

    /**
     * Constructor with default choice algorthim of random
     * @param name of computer player 
     */
    public ComputerPlayer(String name) {
        this.name = name;
        this.choiceAlgorithm = new randomChoice();
    }

    /**
     * Constructs a ComputerPlayer with the specified name and algorithm.
     *
     * @param name the name of the computer player
     * @param choiceAlgorithm the move-selection algorithm to use
     */
    public ComputerPlayer(String name, ChoiceAlgorithm choiceAlgorithm) {
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
}
