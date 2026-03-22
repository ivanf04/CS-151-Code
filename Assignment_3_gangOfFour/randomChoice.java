package Assignment_3_gangOfFour;
import java.util.Random;
/**
 * Random choice algorithm for computer player
 */
public class randomChoice implements ChoiceAlgrorithm {

    private Random random = new Random();
    public randomChoice(){}

    /**
     * Outputs a move (Rock, paper or scissors) at random 
     * 
     * @return the randomly chosen move 
     */
    @Override
    public Move determineMove() {
        int choice = random.nextInt(3) + 1;
        return Move.fromInt(choice);
    }
}