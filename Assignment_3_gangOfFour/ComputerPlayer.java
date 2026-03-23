package Assignment_3_gangOfFour;

/**
* A Player controlled by the computer. Chooses moves randomly.
*/
public class ComputerPlayer implements Player {

    private final String name;
    private final ChoiceAlgrorithm choiceAlgrorithm;

    /**
    * Constructs a ComputerPlayer with the specified name.
    *
    * @param name the name of the computer player
    */
    public ComputerPlayer(String name) {
        this.name = name;
        this.choiceAlgrorithm = new randomChoice();
    }

    /**
     * overloaded constructor to set algorithm 
     * 
     * @param name name of computer player
     * @param random T == random, F == ML choice
     */
    public ComputerPlayer(String name, boolean random) {
        this.name = name;
        if (random) {
            this.choiceAlgrorithm = new randomChoice();
        }else{
            this.choiceAlgrorithm = new MLChoice();
        }
        
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
        Move move = choiceAlgrorithm.determineMove();
        return move;
    }
}
