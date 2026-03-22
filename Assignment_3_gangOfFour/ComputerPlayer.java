package Assignment_3_gangOfFour;

/**
* A Player controlled by the computer. Chooses moves randomly.
*/
public class ComputerPlayer implements Player {

    private final String name;
    private final ChoiceAlgrorithm randomChoice;

     /**
    * Constructs a ComputerPlayer with the specified name.
    *
    * @param name the name of the computer player
    */
    public ComputerPlayer(String name) {
        this.name = name;
        this.randomChoice = new randomChoice();
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
    * Randomly selects and returns a Move.
    *
    * @return a randomly chosen Move (ROCK, PAPER, or SCISSORS)
    */
    @Override
    public Move getMove() {
        Move move = randomChoice.determineMove();
        return move;
    }
}
