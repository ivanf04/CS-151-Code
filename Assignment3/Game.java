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

        
        for (int round = 1; round <= 20; round++) {
            System.out.printf("Round %d - Choose (1=rock, 2=paper, 3=scissors): ", round);
            Move humanMove = humanPlayer.getMove();
            Move computerMove = computerPlayer.getMove();
            Result result = classicRPS.determineOutcome(humanMove, computerMove);
            scoreboard.updateScore(result);
            System.out.printf("You choose %s The computer chose %s", humanMove, computerMove);
            //scoreboard.printOutcome();
            System.out.println();
        }
        scoreboard.printScore();
    }
}