package Assignment_4_gangOfFour;

import javafx.scene.control.Alert;

public class GameController {
    /**
 * --- THE CONTROLLER ---
 * This handles the logic and controls the UI 
 * and the Game/Scoreboard (Model).
 */
class GameController {
    private GUI_Interface view;
    
    // TODO : Instantiate the Backend classes used in Game.java

    public GameController(GUI_Interface view) {
        this.view = view;
        
        // TODO : Initialize your Game, Player, and ML instances 
       
    }

    public void handlePlayerMove(String move) {
        // Update the view to show what the human clicked immediately
        view.updateHumanChoice(move);
        view.updateStatus("Calculating computer move...");

        // TODO : (Basically use Game.java logic here)
        // 1. Pass 'move' to the backend Game or Player class.
        // 2. Trigger the computer's prediction and move generation.
        // 3. Determine the winner via classicRPS.determineOutcome().
        // 4. Update the scoreboard.
        // 5. Retrieve the new round number, choices, winner, and stats.


        // Data Below is Temp!!!
        // --- PLACEHOLDER DATA: Replace these variables with actual backend calls ---
        int currentRound = 6; 
        String predictedChoice = "Paper"; // e.g., computerPlayer.getPrediction()
        String computerChoice = "Scissors"; // e.g., computerPlayer.getMove()
        String winner = "Computer"; // e.g., classicRPS.getWinnerString()
        
        int humanWins = 2; // e.g., scoreboard.getHumanWins()
        int compWins = 3;  // e.g., scoreboard.getComputerWins()
        int currentTies = 0; // e.g., scoreboard.getTies()
        // --------------------------------------------------------------------------

        // Push the backend data back to the GUI View
        view.updateRound(currentRound);
        view.updateComputerData(predictedChoice, computerChoice, winner);
        view.updateStatistics(humanWins, compWins, currentTies);
        view.updateStatus("Round complete. Awaiting next move...");
    }

    public void handleNewGame() {

        
        // TODO : Reset the backend Scoreboard, MoveHistory, and Game state.


        
        // Reseting the UI
        view.updateRound(1);
        view.updateHumanChoice("--");
        view.updateComputerData("--", "--", "--");
        view.updateStatistics(0, 0, 0);
        view.updateStatus("New game started. Good luck!");
    }

    public void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Rock Paper Scissors ML");
        alert.setContentText("Assignment 4 - Gang of Four\nA game that predicts your moves!");
        alert.showAndWait();
    }
}
    
}
