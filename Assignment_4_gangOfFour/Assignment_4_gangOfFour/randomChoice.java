package Assignment_4_gangOfFour;

import java.util.Random;

/**
 * Random choice algorithm for the computer player.
 */
public class randomChoice implements ChoiceAlgorithm {

    private final Random random;

    public randomChoice() {
        this.random = new Random();
    }

    /**
     * Outputs a move (rock, paper, or scissors) at random.
     *
     * @return the randomly chosen move
     */
    @Override
    public Move determineMove() {
        int choice = random.nextInt(3) + 1;
        return Move.fromInt(choice);
    }
}
