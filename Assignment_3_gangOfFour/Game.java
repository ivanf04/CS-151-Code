
package Assignment_3_gangOfFour;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
* Coordinates gameplay for a Rock-Paper-Scissors match.
* Runs a fixed number of rounds, prints results, and updates the scoreboard.
*/
public class Game {
    
    private final Player humanPlayer;
    private final Player computerPlayer;
    private final ClassicRPS classicRPS;
    private final Scoreboard scoreboard;
    private final Scanner scanner = new Scanner(System.in);

    /**
    * Constructs a Game with the specified players, rule set, and scoreboard.
    *
    * @param humanPlayer the human player
    * @param computerPlayer the computer player
    * @param classicRPS the rules used to determine round outcomes
    * @param scoreboard the scoreboard used to track results
    */
    public Game(Player humanPlayer,
                Player computerPlayer,
                ClassicRPS rule,
                Scoreboard scoreboard) {

        this.humanPlayer = humanPlayer;
        this.computerPlayer = computerPlayer;
        this.classicRPS = rule;
        this.scoreboard = scoreboard;
    }
    
    private int getNumRounds() {
        boolean valid = false;
        int numRounds = 0;
        do {
            try {
                 System.out.print("How many rounds would you like to play? ");
                 numRounds = scanner.nextInt();
                 valid = true;
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input, please enter a valid integer.");
                scanner.nextLine();
            }
        } while (!valid);
         
        return numRounds; 
    }

    /**
    * Plays 20 rounds of Rock-Paper-Scissors.
    * Each round: gets both moves, determines outcome, prints round summary,
    * updates score, and prints running score. Prints final score after 20 rounds.
    */
    public void play() {

        System.out.println("Starting Rock-Paper-Scissors Game.");
        int numRounds = getNumRounds();
        
        // Each round is one iteration of the loop 
        for (int round = 1; round <= numRounds; round++) {
            System.out.printf("Round %d - Choose (rock = 1, paper = 2, scissors = 3):", round);  //display round number input options 
            Move humanMove = humanPlayer.getMove(); 
            Move computerMove = computerPlayer.getMove();  
            Result result = classicRPS.determineOutcome(humanMove, computerMove);   
            scoreboard.updateScore(result);
            System.out.printf("You choose %s The computer chose %s. ", humanMove, computerMove); //display the selected moves of each player
            scoreboard.displayRoundWinner(result); //display the winer of the round 
            System.out.println();
            scoreboard.printScore();
            System.out.println();
        }
        
        // Game has ended, display "Game over" and final score of all rounds
        System.out.println("Game over. Thanks for playing.");
        scoreboard.printScore();
    }
}
