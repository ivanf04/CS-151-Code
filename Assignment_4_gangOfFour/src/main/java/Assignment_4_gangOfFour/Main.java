package Assignment_4_gangOfFour;

import java.util.*;

/**
 * Simple test program to run gameplay of rock paper scissors (RPS)
 * 
 * To compile:
 * javac Assignment_4_gangOfFour/*.java
 *
 * To run:
 * java Assignment_4_gangOfFour.Main -r   // random algorithm
 * java Assignment_4_gangOfFour.Main -m   // machine learning algorithm
 *
 * If no argument is provided, the program defaults to random choice.
 * 
 * Design fixes:
 * Problem #1 (Interface design):
 * - The ComputerPlayer no longer chooses the algorithm internally via boolean constructor.
 * - Instead, the ChoiceAlgorithm is created here (in Main) and passed 
 * into the ComputerPlayer constructor.
 *
 * Problem #3 (Algorithm swapping/CLI handling):
 * - Command-line parsing is handled more cleanly and explicitly.
 * - Both "-r" and "-m" are supported, and invalid inputs (or none) are handled.
 * - The ChoiceFactory centralizes algorithm selection, making it easy
 *   to extend or modify in the future.
 * 
 */

public class Main {

    public static void main(String[] args) {

        MoveHistory moveHistory = new MoveHistory();
        Scanner scanner = new Scanner(System.in);

        // Default algorithm option
        String option = "-r";

        // Handle CLI arguments
        if (args.length == 1) {
            option = args[0];

            if (!option.equals("-r") && !option.equals("-m")) {
                System.out.println("Invalid argument: " + option);
                return;
            }
        }

        ChoiceAlgorithm algorithm = ChoiceFactory.createChoice(option, moveHistory);

        Player human = new HumanPlayer("Human", scanner);
        ComputerPlayer computer = new ComputerPlayer("Computer", algorithm);

        ClassicRPS rule = new ClassicRPS();
        Scoreboard scoreboard = new Scoreboard();

        Game game = new Game(human, computer, rule, scoreboard, moveHistory);
        game.play();

        // Save ML data after game ends (don't save after every round)
        moveHistory.saveData();

        scanner.close();
    }
}
