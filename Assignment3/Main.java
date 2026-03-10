package Assignment3;

import java.util.Scanner;

public class Main {
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
