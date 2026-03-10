package Assignment3;

import java.util.Scanner;

public class HumanPlayer implements Player {

    private final String name;
    private final Scanner scanner;

    public HumanPlayer(String name, Scanner scanner) {
        this.name = name;
        this.scanner = scanner;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Move getMove() {
        while (true) {
            try {
                System.out.print("Choose (rock = 1, paper = 2, scissors = 3): ");
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