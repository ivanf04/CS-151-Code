package Assignment_3_gangOfFour;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class RpsTests {

    private static int testsRun = 0;
    private static int testsPassed = 0;

    private static void check(String testName, boolean condition) {
        testsRun++;
        if (condition) {
            testsPassed++;
            System.out.println("[PASS] " + testName);
        } else {
            System.out.println("[FAIL] " + testName);
        }
    }

    private static HumanPlayer makeHumanPlayerWithInput(String input) {
        ByteArrayInputStream in
                = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        Scanner scanner = new Scanner(in);
        return new HumanPlayer("Bob", scanner);
    }

    private static class StubPlayer implements Player {

        private final String name;
        private final Move move;

        StubPlayer(String name, Move move) {
            this.name = name;
            this.move = move;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public Move getMove() {
            return move;
        }
    }

    public static void main(String[] args) {
        testMove();
        testClassicRPS();
        testHumanPlayer();
        testComputerPlayer();
        testScoreboard();
        testGame();

        System.out.println();
        System.out.println("Tests passed: " + testsPassed + "/" + testsRun);
    }

    private static void testMove() {
        System.out.println("=== Move Tests ===");
        check("Move.fromInt(1) returns ROCK", Move.fromInt(1) == Move.ROCK);
        check("Move.fromInt(2) returns PAPER", Move.fromInt(2) == Move.PAPER);
        check("Move.fromInt(3) returns SCISSORS", Move.fromInt(3) == Move.SCISSORS);
        check("Move.fromInt(0) returns null", Move.fromInt(0) == null);
        check("Move.fromInt(4) returns null", Move.fromInt(4) == null);
        check("Move.fromInt(-1) returns null", Move.fromInt(-1) == null);
        check("ROCK getValue() is 1", Move.ROCK.getValue() == 1);
        check("PAPER getValue() is 2", Move.PAPER.getValue() == 2);
        check("SCISSORS getValue() is 3", Move.SCISSORS.getValue() == 3);
        System.out.println();
    }

    private static void testClassicRPS() {
        System.out.println("=== ClassicRPS Tests ===");
        ClassicRPS rules = new ClassicRPS();

        check("ROCK beats SCISSORS",
                rules.determineOutcome(Move.ROCK, Move.SCISSORS) == Result.HUMAN_WIN);
        check("ROCK loses to PAPER",
                rules.determineOutcome(Move.ROCK, Move.PAPER) == Result.COMPUTER_WIN);
        check("PAPER beats ROCK",
                rules.determineOutcome(Move.PAPER, Move.ROCK) == Result.HUMAN_WIN);
        check("PAPER loses to SCISSORS",
                rules.determineOutcome(Move.PAPER, Move.SCISSORS) == Result.COMPUTER_WIN);
        check("SCISSORS beats PAPER",
                rules.determineOutcome(Move.SCISSORS, Move.PAPER) == Result.HUMAN_WIN);
        check("SCISSORS loses to ROCK",
                rules.determineOutcome(Move.SCISSORS, Move.ROCK) == Result.COMPUTER_WIN);

        check("ROCK vs ROCK is DRAW",
                rules.determineOutcome(Move.ROCK, Move.ROCK) == Result.DRAW);
        check("PAPER vs PAPER is DRAW",
                rules.determineOutcome(Move.PAPER, Move.PAPER) == Result.DRAW);
        check("SCISSORS vs SCISSORS is DRAW",
                rules.determineOutcome(Move.SCISSORS, Move.SCISSORS) == Result.DRAW);
        System.out.println();
    }

    private static void testHumanPlayer() {
        System.out.println("=== HumanPlayer Tests ===");

        check("HumanPlayer getName returns Bob",
                makeHumanPlayerWithInput("1\n").getName().equals("Bob"));

        check("Input 1 returns ROCK",
                makeHumanPlayerWithInput("1\n").getMove() == Move.ROCK);

        check("Input 2 returns PAPER",
                makeHumanPlayerWithInput("2\n").getMove() == Move.PAPER);

        check("Input 3 returns SCISSORS",
                makeHumanPlayerWithInput("3\n").getMove() == Move.SCISSORS);

        check("Invalid inputs then 1 returns ROCK",
                makeHumanPlayerWithInput("4\n0\n1\n").getMove() == Move.ROCK);

        check("Non-integer then 2 returns PAPER",
                makeHumanPlayerWithInput("abc\n2\n").getMove() == Move.PAPER);

        check("Blank line then 3 returns SCISSORS",
                makeHumanPlayerWithInput("\n3\n").getMove() == Move.SCISSORS);

        check("Input with spaces returns valid move",
                makeHumanPlayerWithInput("   1   \n").getMove() == Move.ROCK);

        System.out.println();
    }

    private static void testComputerPlayer() {
        System.out.println("=== ComputerPlayer Tests ===");

        ComputerPlayer player = new ComputerPlayer("Computer1");
        check("ComputerPlayer getName returns Computer1",
                player.getName().equals("Computer1"));

        boolean allValid = true;
        for (int i = 0; i < 1000; i++) {
            Move move = player.getMove();
            if (move != Move.ROCK && move != Move.PAPER && move != Move.SCISSORS) {
                allValid = false;
                break;
            }
        }
        check("ComputerPlayer always returns a valid move", allValid);

        System.out.println();
    }

    private static void testScoreboard() {
        System.out.println("=== Scoreboard Tests ===");

        Scoreboard scoreboard = new Scoreboard();
        check("Initial humanWins is 0", scoreboard.getHumanWins() == 0);
        check("Initial computerWins is 0", scoreboard.getComputerWins() == 0);
        check("Initial draws is 0", scoreboard.getDraws() == 0);

        scoreboard = new Scoreboard();
        scoreboard.humanWin();
        check("humanWin increments only humanWins",
                scoreboard.getHumanWins() == 1
                && scoreboard.getComputerWins() == 0
                && scoreboard.getDraws() == 0);

        scoreboard = new Scoreboard();
        scoreboard.computerWin();
        check("computerWin increments only computerWins",
                scoreboard.getHumanWins() == 0
                && scoreboard.getComputerWins() == 1
                && scoreboard.getDraws() == 0);

        scoreboard = new Scoreboard();
        scoreboard.draw();
        check("draw increments only draws",
                scoreboard.getHumanWins() == 0
                && scoreboard.getComputerWins() == 0
                && scoreboard.getDraws() == 1);

        scoreboard = new Scoreboard();
        scoreboard.updateScore(Result.HUMAN_WIN);
        check("updateScore(HUMAN_WIN) works",
                scoreboard.getHumanWins() == 1
                && scoreboard.getComputerWins() == 0
                && scoreboard.getDraws() == 0);

        scoreboard = new Scoreboard();
        scoreboard.updateScore(Result.COMPUTER_WIN);
        check("updateScore(COMPUTER_WIN) works",
                scoreboard.getHumanWins() == 0
                && scoreboard.getComputerWins() == 1
                && scoreboard.getDraws() == 0);

        scoreboard = new Scoreboard();
        scoreboard.updateScore(Result.DRAW);
        check("updateScore(DRAW) works",
                scoreboard.getHumanWins() == 0
                && scoreboard.getComputerWins() == 0
                && scoreboard.getDraws() == 1);

        scoreboard = new Scoreboard();
        scoreboard.updateScore(Result.HUMAN_WIN);
        scoreboard.updateScore(Result.COMPUTER_WIN);
        scoreboard.updateScore(Result.DRAW);
        scoreboard.updateScore(Result.HUMAN_WIN);
        scoreboard.updateScore(Result.DRAW);
        check("Mixed sequence totals are correct",
                scoreboard.getHumanWins() == 2
                && scoreboard.getComputerWins() == 1
                && scoreboard.getDraws() == 2);

        System.out.println();
    }

    private static void testGame() {
        System.out.println("=== Game Tests ===");

        ClassicRPS rules = new ClassicRPS();

        Scoreboard scoreboard1 = new Scoreboard();
        Game game1 = new Game(
                new StubPlayer("Human", Move.ROCK),
                new StubPlayer("Computer", Move.SCISSORS),
                rules,
                scoreboard1
        );
        game1.play();
        check("Game can produce 20 human wins",
                scoreboard1.getHumanWins() == 20
                && scoreboard1.getComputerWins() == 0
                && scoreboard1.getDraws() == 0);

        Scoreboard scoreboard2 = new Scoreboard();
        Game game2 = new Game(
                new StubPlayer("Human", Move.ROCK),
                new StubPlayer("Computer", Move.ROCK),
                rules,
                scoreboard2
        );
        game2.play();
        check("Game can produce 20 draws",
                scoreboard2.getHumanWins() == 0
                && scoreboard2.getComputerWins() == 0
                && scoreboard2.getDraws() == 20);

        Scoreboard scoreboard3 = new Scoreboard();
        Game game3 = new Game(
                new StubPlayer("Human", Move.SCISSORS),
                new StubPlayer("Computer", Move.ROCK),
                rules,
                scoreboard3
        );
        game3.play();
        check("Game can produce 20 computer wins",
                scoreboard3.getHumanWins() == 0
                && scoreboard3.getComputerWins() == 20
                && scoreboard3.getDraws() == 0);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out));

        try {
            Scoreboard scoreboard4 = new Scoreboard();
            Game game4 = new Game(
                    new StubPlayer("Human", Move.ROCK),
                    new StubPlayer("Computer", Move.ROCK),
                    rules,
                    scoreboard4
            );
            game4.play();
        } finally {
            System.setOut(originalOut);
        }

        String output = out.toString();

        int roundCount = 0;
        for (int i = 1; i <= 20; i++) {
            if (output.contains("Round " + i + " - Choose")) {
                roundCount++;
            }
        }

        check("Game prints 20 round prompts", roundCount == 20);
        check("Game prints starting message",
                output.contains("Starting Rock-Paper-Scissors Game."));
        check("Game prints final score line",
                output.contains("Score: Human:"));

        System.out.println();
    }
}
