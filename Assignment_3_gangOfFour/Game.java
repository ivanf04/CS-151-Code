/*
    This class implements the game rock, paper, scissors (RPS). Its contructor initilizes all necesary objects to play a game 
    of RPS. Member funcitons include displayRoundWinner() and play(). The play() function gets the "moves" from both the human
    and computer players. It then displays each player's choices and the winner of the round. 
    Round wins and draws are saved and displayed at the end of 20 rounds.  
 */
package Assignment_3_gangOfFour;

public class Game {

    /*
    * instance variables for each game object, computer player, human player, Game rule set (classic RPS),
    * and scoreboard
    */
    private final Player humanPlayer;
    private final Player computerPlayer;
    private final ClassicRPS classicRPS;
    private final Scoreboard scoreboard;

    //public Game constructor 
    public Game(Player humanPlayer,
                Player computerPlayer,
                ClassicRPS rule,
                Scoreboard scoreboard) {

        this.humanPlayer = humanPlayer;
        this.computerPlayer = computerPlayer;
        this.classicRPS = rule;
        this.scoreboard = scoreboard;
    }
    /*
        function that orchestrates a playthrough of RPS, set to 20 rounds as specified in assingment specs     
    */
    public void play() {

        System.out.println("Starting Rock-Paper-Scissors Game.");

        //Each round is one iteration of the loop 
        for (int round = 1; round <= 20; round++) {
            System.out.printf("Round %d - Choose (rock = 1, paper = 2, scissors = 3):", round); //display round number input options 
            Move humanMove = humanPlayer.getMove(); 
            Move computerMove = computerPlayer.getMove();  
            Result result = classicRPS.determineOutcome(humanMove, computerMove);   
            scoreboard.updateScore(result);
            System.out.printf("You choose %s The computer chose %s. ", humanMove, computerMove);    //display the selected moves of each player
            scoreboard.displayRoundWinner(result); //display the winer of the round 
            System.out.println();
            scoreboard.printScore();
            System.out.println();
        }
        
        //game has ended, display "Game over" and final score of all rounds. 
        System.out.println("Game over. Thanks for playing.");
        scoreboard.printScore();
    }
}