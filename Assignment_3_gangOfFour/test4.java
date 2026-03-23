package Assignment_3_gangOfFour;
import java.util.*;

/**
 * Test file for the ML algorithm 
 */
public class test4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Player human = new HumanPlayer("Human", scanner);
        Player mLComputer = new ComputerPlayer("ML", false);
        ClassicRPS rule = new ClassicRPS();
        Scoreboard scoreboard = new Scoreboard();
        Game game = new Game(human, mLComputer, rule, scoreboard);
        game.play();

    }
}
