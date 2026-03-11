package Assignment_3_gangOfFour;

import java.util.Scanner;

/**
* A Player controlled by a human user via console input.
* Prompts until a valid move choice is entered.
*/
public class HumanPlayer implements Player {

    private final String name;
    private final Scanner scanner;

    /**
    * Constructs a HumanPlayer with a name and a Scanner for input.
    *
    * @param name the player's name
    * @param scanner the Scanner used to read user input
    */
    public HumanPlayer(String name, Scanner scanner) {
        this.name = name;
        this.scanner = scanner;
    }

    /**
    * Returns the human player's name.
    *
    * @return the player's name
    */
    @Override
    public String getName() {
        return name;
    }

    /**
    * Prompts the user to choose a move and validates the input.
    * Continues prompting until the user enters 1, 2, or 3.
    *
    * @return the Move selected by the user
    */
    @Override
    public Move getMove() {
        while (true) {
            try {
                //System.out.print("Choose (rock = 1, paper = 2, scissors = 3): ");
                int input = Integer.parseInt(scanner.nextLine().trim());

                Move move = Move.fromInt(input);

                if (move != null) {
                    return move;
                }

                System.out.println("Invalid input. Please enter 1, 2, or 3.");

            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}
