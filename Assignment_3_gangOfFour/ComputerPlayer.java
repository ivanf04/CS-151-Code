package Assignment_3_gangOfFour;
import java.util.Random;

/**
* A Player controlled by the computer. Chooses moves randomly.
*/
public class ComputerPlayer implements Player {

    private final String name;
    private final Random random = new Random();

     /**
    * Constructs a ComputerPlayer with the specified name.
    *
    * @param name the name of the computer player
    */
    public ComputerPlayer(String name) {
        this.name = name;
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
        int choice = random.nextInt(3) + 1;
        return Move.fromInt(choice);
    }
}
