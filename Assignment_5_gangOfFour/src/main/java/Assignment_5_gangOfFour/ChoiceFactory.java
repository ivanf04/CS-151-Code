package Assignment_5_gangOfFour;

/**
 * Factory class used to create the computer's choice algorithm 
 * based on the command-line option.
 */
public class ChoiceFactory {
    /**
     * Creates the appropriate choice algorithm from a command-line option.
     *
     * Supported options:
     * - "-r" for random choice
     * - "-m" for machine learning choice
     *
     * @param option the command-line option
     * @param moveHistory the shared MoveHistory object used by MLChoice
     * @return the requested ChoiceAlgorithm
     * @throws IllegalArgumentException if the option is invalid
     */
    public static ChoiceAlgorithm createChoice(String option, MoveHistory moveHistory) {
        if (option.equals("-r")) {
            return new randomChoice();
        }

        if (option.equals("-m")) {
            return new MLChoice(moveHistory);
        }

        throw new IllegalArgumentException("Invalid option: " + option);
    }
}