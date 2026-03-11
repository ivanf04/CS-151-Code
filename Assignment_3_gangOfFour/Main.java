/*
  Simple test program to run a playthrough of rock paper scissors(RPS)
*/
package Assignment_3_gangOfFour;
import java.util.Scanner;

public class Main {
  /*
    Create player, game rule and scoreboard objects. 
    Pass them as arguments to Game() constructor and run game.play
  */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Player bob = new HumanPlayer("Bob", scanner);
    Player computer = new ComputerPlayer("Computer1");
    ClassicRPS rule = new ClassicRPS();
    Scoreboard scoreboard = new Scoreboard();
    Game game = new Game(bob, computer, rule, scoreboard);
    game.play();
  }
}
