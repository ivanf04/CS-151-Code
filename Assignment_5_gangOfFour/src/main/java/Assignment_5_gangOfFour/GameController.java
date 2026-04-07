package Assignment_5_gangOfFour;

import javafx.scene.control.Alert;

/**
 * Controller for the JavaFX Rock-Paper-Scissors GUI. Connects the view with the
 * backend game logic.
 */
public class GameController {

    private final GUI_Interface view;

    // Backend / model objects
    private MoveHistory moveHistory;
    private Scoreboard scoreboard;
    private ClassicRPS classicRPS;
    private ChoiceAlgorithm algorithm;
    private ComputerPlayer computerPlayer;

    // GUI game state
    private int currentRound;
    private int maxRounds;     
    private boolean isMLMode; 

    /**
     * Constructs the controller. Backend objects are initialized when 
     * startGame() is called.
     *
     * @param view the GUI view
     */
    public GameController(GUI_Interface view) {
        this.view = view;
        this.moveHistory = new MoveHistory();
    }

    /**
     * Triggered when the user clicks [Start GAME] on the setup screen.
     * * @param isML true if ML opponent was selected, false if Random
     * @param rounds the maximum number of rounds to play
     */
    public void startGame(boolean isML, int rounds) {
        this.isMLMode = isML;
        this.maxRounds = rounds;
        this.currentRound = 1;

        // Reset the backend logic for a new game session
        this.scoreboard = new Scoreboard();
        this.classicRPS = new ClassicRPS();

        // If isML is true, it should pass false to ChoiceFactory (MLChoice).
        // If isML is false, it should pass true to ChoiceFactory (randomChoice).
        
        String mode = isML ? "-m" : "-r"; 
        this.algorithm = ChoiceFactory.createChoice(mode, moveHistory);        this.computerPlayer = new ComputerPlayer("Computer", algorithm);

        // Transition the GUI to the game screen
        view.showGameScreen(maxRounds);
        view.updateRound(currentRound);
        view.updateHumanChoice("--");
        view.updateComputerData("--", "--", "--");
        view.updateStatistics(0, 0, 0);
        
        String modeString = isML ? "Machine Learning" : "Random";
        view.updateStatus("Game started against " + modeString + " Opponent! (Best of " + maxRounds + ")");
    }

    /**
     * Handles the human player's move from the GUI.
     *
     * @param move the human move as a string ("Rock", "Paper", or "Scissors")
     */
    public void handlePlayerMove(String move) {
        if (currentRound > maxRounds) {
            view.updateStatus("Game Over! Please click File -> New Game.");
            return;
        }

        Move humanMove = stringToMove(move);
        view.updateHumanChoice(formatMove(humanMove));
        view.updateStatus("Calculating computer move...");

        // Determine prediction before the computer move is generated
        Move predictedHumanMove = predictHumanMove();

        // Trigger the computer's move
        Move computerMove = computerPlayer.getMove();
        Result result = classicRPS.determineOutcome(humanMove, computerMove);

        // Update backend data
        scoreboard.updateScore(result);
        moveHistory.recordRound(humanMove, computerMove);

        // Format for display
        String predictedChoiceText = (predictedHumanMove == null) ? "--" : formatMove(predictedHumanMove);
        
        if (!isMLMode) {
            predictedChoiceText = "--";
        }

        String computerChoiceText = formatMove(computerMove);
        String winnerText = resultToDisplay(result);

        // Update the View
        view.updateComputerData(predictedChoiceText, computerChoiceText, winnerText);
        view.updateStatistics(
                scoreboard.getHumanWins(),
                scoreboard.getComputerWins(),
                scoreboard.getDraws()
        );

        if (currentRound == maxRounds) {
            view.updateStatus("Game Over! See statistics above. Go to File -> New Game to play again.");
        } else {
            view.updateStatus("Round " + currentRound + " complete. Awaiting next move...");
        }

        view.updateRound(currentRound);
        currentRound++;
    }

    /**
     * Starts a new game by returning to the Setup screen.
     */
    public void handleNewGame() {
        // Save current learned data before resetting
        if (moveHistory != null) {
            moveHistory.saveData();
        }

        // Return the user to the Setup screen
        view.showSetupScreen();
        view.updateStatus("Waiting for setup...");
    }

    /**
     * Displays the About dialog.
     */
    public void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Rock Paper Scissors ML");
        alert.setContentText("Assignment 5 - Gang of Four\nA game that predicts your moves!"
           + "\n\nHow to Play:\n\tSelect opponent, ML (machine learing) or random"
           + "\n\tEnter the number of rounds\n\tPress \"Start Game\"");
        alert.showAndWait();
    }

    // --- Helper Formatting Methods Below (Unchanged) ---

    private Move stringToMove(String move) {
        if (move == null) throw new IllegalArgumentException("Move cannot be null.");
        switch (move.trim().toLowerCase()) {
            case "rock": return Move.ROCK;
            case "paper": return Move.PAPER;
            case "scissors": return Move.SCISSORS;
            default: throw new IllegalArgumentException("Invalid move: " + move);
        }
    }

    private Move predictHumanMove() {
        if (!moveHistory.hasFullPrefix()) return null;
        String prefix = moveHistory.getCurrentPrefix();
        int rock = moveHistory.getFrequency(prefix + "R");
        int paper = moveHistory.getFrequency(prefix + "P");
        int scissors = moveHistory.getFrequency(prefix + "S");

        if (rock == 0 && paper == 0 && scissors == 0) return null;
        Move predicted = Move.ROCK;
        int max = rock;
        if (paper > max) { predicted = Move.PAPER; max = paper; }
        if (scissors > max) { predicted = Move.SCISSORS; }
        return predicted;
    }

    private String formatMove(Move move) {
        switch (move) {
            case ROCK: return "Rock";
            case PAPER: return "Paper";
            case SCISSORS: return "Scissors";
            default: throw new IllegalArgumentException("Invalid move.");
        }
    }

    private String resultToDisplay(Result result) {
        switch (result) {
            case HUMAN_WIN: return "Human";
            case COMPUTER_WIN: return "Computer";
            case DRAW: return "Draw";
            default: throw new IllegalArgumentException("Invalid result.");
        }
    }
}