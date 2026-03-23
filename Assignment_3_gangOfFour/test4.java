package Assignment_3_gangOfFour;
import java.util.*;

/**
 * Test file for the ML algorithm 
 */
public class test4 {
    public static void main(String[] args) {
        MoveHistory moveHistory = new MoveHistory();
        Scanner scanner = new Scanner(System.in);
        Player human = new HumanPlayer("Human", scanner);
        Player mLComputer = new ComputerPlayer("ML", false, moveHistory);
        ClassicRPS rule = new ClassicRPS();
        Scoreboard scoreboard = new Scoreboard();
        Game game = new Game(human, mLComputer, rule, scoreboard, moveHistory);
        game.play();
    }
}
