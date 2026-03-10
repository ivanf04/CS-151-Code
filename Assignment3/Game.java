package Assignment3;

//import java.util.Scanner;

public class Game {

    private final Player humanPlayer;
    private final Player computerPlayer;
    private final ClassicRPS classicRPS;
    private final Scoreboard scoreboard;

    public Game(Player humanPlayer,
                Player computerPlayer,
                ClassicRPS rule,
                Scoreboard scoreboard) {

        this.humanPlayer = humanPlayer;
        this.computerPlayer = computerPlayer;
        this.classicRPS = rule;
        this.scoreboard = scoreboard;
    }

    public void play() {

        System.out.println("Starting Rock-Paper-Scissors Game.");

        
        for (int round = 1; round <= 5; round++) {
            System.out.printf("Round %d - ", round);
            Move humanMove = humanPlayer.getMove();
            Move computerMove = computerPlayer.getMove();
            Result result = classicRPS.determineOutcome(humanMove, computerMove);
            scoreboard.updateScore(result);
            System.out.printf("You choose %s The computer chose %s", humanMove, computerMove);
            //TODO: make function somewhere to display who won the current round
            System.out.println();
            scoreboard.printScore();
            System.out.println();
        }
        //TODO: Display winner
        System.out.println("Game over. Thanks for playing.");
        scoreboard.printScore();
    }
}