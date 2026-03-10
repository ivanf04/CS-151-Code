package Assignment3;

import java.util.Scanner;

public class Game {

    private final Player humanPlayer;
    private final Player computerPlayer;
    private final Rules rules;
    private final Scoreboard scoreboard;

    public Game(Player humanPlayer,
                Player computerPlayer,
                Rules rules,
                Scoreboard scoreboard) {

        this.humanPlayer = humanPlayer;
        this.computerPlayer = computerPlayer;
        this.rules = rules;
        this.scoreboard = scoreboard;
    }

    public void play() {

        System.out.println("Starting Rock-Paper-Scissors Game.");

        for (int round = 1; round <= 20; round++) {
          System.out.printf("Round %d - Choose (1=rock, 2=paper, 3=scissors): ", round)


        }

        #do something with scoreboard
    }
}