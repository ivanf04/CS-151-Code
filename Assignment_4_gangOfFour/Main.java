package Assignment_4_gangOfFour;

/**
 * Simple test program to run a playthrough of rock paper scissors(RPS)
 * 
 * To compile and run the project,
 * javac Assignment_4_gangOfFour/*.java
 * 
 * then to run Main.java
 * java Assignment_4_gangOfFour.Main -m
 */
import java.util.*;

/**
 * Test file for the ML algorithm 
 * Run "java test4 -r" for random computer move selection
 * Run "java test4 -m" for ML computer move selection
 */
public class Main {
    public static void main(String[] args) {
        MoveHistory moveHistory = new MoveHistory();
        Scanner scanner = new Scanner(System.in);

        Player human = new HumanPlayer("Human", scanner);

        //Default = random 
        boolean useRandom = true; 

        //read CLI argument
        if(args.length > 0){
            if(args[0].equals("-m"))
                useRandom = false;
            } else if (args[0].equals("-r")) {
            useRandom = true;
        }

        Player mLComputer = new ComputerPlayer("ML", useRandom, moveHistory);

        ClassicRPS rule = new ClassicRPS();
        Scoreboard scoreboard = new Scoreboard();

        Game game = new Game(human, mLComputer, rule, scoreboard, moveHistory);
        game.play();
    }
}
