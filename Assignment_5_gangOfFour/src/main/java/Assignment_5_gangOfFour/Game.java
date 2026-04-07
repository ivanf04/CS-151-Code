package Assignment_5_gangOfFour;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Coordinates gameplay for a Rock-Paper-Scissors match.
 * Runs a certain number of rounds, prints results, updates the scoreboard,
 * and records move history after each round.
 */
public class Game {

    private final Player humanPlayer;
    private final Player computerPlayer;
    private final ClassicRPS classicRPS;
    private final Scoreboard scoreboard;
    private final MoveHistory moveHistory;
    private final Scanner scanner;


    /**
     * Constructs a Game with the specified players, rule set, scoreboard,
     * and move history.
     *
     * @param humanPlayer the human player
     * @param computerPlayer the computer player
     * @param rule the rules used to determine round outcomes
     * @param scoreboard the scoreboard used to track results
     */
    public Game(Player humanPlayer,
                ComputerPlayer computerPlayer,
                ClassicRPS classicRPS,
                Scoreboard scoreboard,
                Scanner scanner
            ) {

        this.humanPlayer = humanPlayer;
        this.computerPlayer = computerPlayer;
        this.classicRPS = classicRPS;
        this.scoreboard = scoreboard;
        this.scanner = scanner;
        this.moveHistory = new MoveHistory();
    }

    /**
     * Constructs a Game with the specified players, rule set, scoreboard,
     * and move history.
     *
     * @param humanPlayer the human player
     * @param mLComputer the computer player
     * @param rule the rules used to determine round outcomes
     * @param scoreboard the scoreboard used to track results
     * @param moveHistory the shared move history
     */
    public Game(Player humanPlayer,
                Player mLComputer,
                ClassicRPS classicRPS,
                Scoreboard scoreboard,
                MoveHistory moveHistory,
                Scanner scanner
            ) {

        this.humanPlayer = humanPlayer;
        this.computerPlayer = mLComputer;
        this.classicRPS = classicRPS;
        this.scoreboard = scoreboard;
        this.moveHistory = moveHistory;
        this.scanner = scanner;
    }
    

    /**
     * Prompts the user for the number of rounds to play.
     *
     * @return the number of rounds
     */
    private int getNumRounds() {
        boolean valid = false;
        int numRounds = 0;

        do {
            try {
                System.out.print("How many rounds would you like to play? ");
                numRounds = scanner.nextInt();

                if (numRounds > 0) {
                    valid = true;
                } else {
                    System.out.println("Please enter a positive integer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input, please enter a valid integer.");
                scanner.nextLine();
            }
        } while (!valid);

        return numRounds;
    }

    /**
     * Plays the requested number of rounds of Rock-Paper-Scissors.
     * Each round: gets both moves, determines outcome, updates score,
     * prints round results, and records the round in shared move history.
     */
    public void play() {
        System.out.println("Starting Rock-Paper-Scissors Game.");   //prompt user for number of rounds
        int numRounds = getNumRounds();

        for (int round = 1; round <= numRounds; round++) {
            System.out.printf("Round %d - Choose (rock = 1, paper = 2, scissors = 3): ", round);

            Move humanMove = humanPlayer.getMove();
            Move computerMove = computerPlayer.getMove();

            Result result = classicRPS.determineOutcome(humanMove, computerMove);
            scoreboard.updateScore(result);

            moveHistory.recordRound(humanMove, computerMove);   //Save moves in MoveHistory 

            System.out.printf("You chose %s. The computer chose %s. ", humanMove, computerMove);    //display round results 
            scoreboard.displayRoundWinner(result);
            System.out.println();
            scoreboard.printScore();
            System.out.println();
        }

        System.out.println("Game over. Thanks for playing.");
        scoreboard.printScore();
    }
}
