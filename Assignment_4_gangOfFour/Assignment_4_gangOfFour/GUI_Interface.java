package Assignment_4_gangOfFour;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GUI_Interface extends Application {

    // --- VIEW ELEMENTS ---
    private Label roundLabel;
    private Label humanChoiceLabel;
    private Label predictedChoiceLabel;
    private Label computerChoiceLabel;
    private Label winnerLabel;
    private Label humanWinsLabel;
    private Label computerWinsLabel;
    private Label tiesLabel;
    private Label statusLabel; //  status bar at the bottom 

    // --- THE CONTROLLER ---
    // This is separated to enforce the MVC pattern.

    private GameController controller;

    @Override
    public void start(Stage primaryStage) {
        controller = new GameController(this);

        primaryStage.setTitle("Rock Paper Scissors: 20 rounds/game");

        // 1. Menu Bar Setup
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem newGameItem = new MenuItem("New Game");
        MenuItem exitItem = new MenuItem("Exit");
        fileMenu.getItems().addAll(newGameItem, exitItem);

        Menu helpMenu = new Menu("Help");
        MenuItem aboutItem = new MenuItem("About");
        helpMenu.getItems().add(aboutItem);

        menuBar.getMenus().addAll(fileMenu, helpMenu);

        // Menu Actions -> Routed to Controller
        newGameItem.setOnAction(e -> controller.handleNewGame());
        exitItem.setOnAction(e -> Platform.exit());
        aboutItem.setOnAction(e -> controller.handleAbout());

        // 2. Main Game Area (VBox)
        VBox mainLayout = new VBox(10); // 10px spacing
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setPadding(new Insets(20));

        // Line 1: Round Number
        roundLabel = new Label("Round: 1");
        roundLabel.setStyle("-fx-font-family: 'Helvetica'; -fx-font-size: 18px; -fx-font-weight: bold;");

        
               
        // Line 2: Human Heading
        Label humanHeader = new Label("Human");
        humanHeader.setStyle("-fx-font-family: 'Helvetica'; -fx-font-size: 18px; -fx-font-weight: bold;");


        // Line 3: HBox for Buttons
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        Label chooseLabel = new Label("Choose:");
        Button rockBtn = new Button("Rock");
        Button paperBtn = new Button("Paper");
        Button scissorsBtn = new Button("Scissors");
        buttonBox.getChildren().addAll(chooseLabel, rockBtn, paperBtn, scissorsBtn);

        // Button Actions -> Routed to Controller
        rockBtn.setOnAction(e -> controller.handlePlayerMove("Rock"));
        paperBtn.setOnAction(e -> controller.handlePlayerMove("Paper"));
        scissorsBtn.setOnAction(e -> controller.handlePlayerMove("Scissors"));

        // Line 4: Human's Choice
        humanChoiceLabel = new Label("Human chooses: --");

        // Line 5: Computer Heading
        Label computerHeader = new Label("Computer");
        computerHeader.setStyle("-fx-font-family: 'Helvetica'; -fx-font-size: 18px; -fx-font-weight: bold;");
        VBox.setMargin(computerHeader, new Insets(15, 0, 0, 0)); // Add top spacing

        // Line 6 & 7: Predictions and Computer Choice
        predictedChoiceLabel = new Label("Predicted human choice: --");
        computerChoiceLabel = new Label("Therefore computer chooses: --");

        // Line 8: The Winner
        HBox winnerBox = new HBox(10);
        winnerBox.setAlignment(Pos.CENTER);
        Label winnerTitle = new Label("The winner:");
        winnerTitle.setStyle("-fx-font-family: 'Helvetica'; -fx-font-size: 18px; -fx-font-weight: bold;");
        winnerLabel = new Label("--");
        winnerBox.getChildren().addAll(winnerTitle, winnerLabel);
        VBox.setMargin(winnerBox, new Insets(15, 0, 15, 0));

        // Statistics Section
        Label statsHeader = new Label("Statistics");
        statsHeader.setStyle("-fx-font-family: 'Helvetica'; -fx-font-size: 18px; -fx-font-weight: bold;");
        humanWinsLabel = new Label("Human wins: 0");
        computerWinsLabel = new Label("Computer wins: 0");
        tiesLabel = new Label("Ties: 0");

        // Add all elements to the VBox
        mainLayout.getChildren().addAll(
                roundLabel,
                humanHeader,
                buttonBox,
                humanChoiceLabel,
                computerHeader,
                predictedChoiceLabel,
                computerChoiceLabel,
                winnerBox,
                statsHeader,
                humanWinsLabel,
                computerWinsLabel,
                tiesLabel
        );

        // Bottom Status Bar
        statusLabel = new Label("Welcome to the Rock Paper Scissors game!");
        statusLabel.setPadding(new Insets(5));
        statusLabel.setStyle("-fx-background-color: #e0e0e0;");
        statusLabel.setMaxWidth(Double.MAX_VALUE);

        // Root Layout (BorderPane to hold Menu, Center, and Bottom)
        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(mainLayout);
        root.setBottom(statusLabel);

        // Scene Setup
        Scene scene = new Scene(root, 450, 500);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    // --- METHODS FOR THE CONTROLLER TO UPDATE THE VIEW ---

    public void updateRound(int round) {
        roundLabel.setText("Round: " + round);
    }

    public void updateHumanChoice(String choice) {
        humanChoiceLabel.setText("Human chooses: " + choice);
    }

    public void updateComputerData(String predicted, String choice, String winner) {
        predictedChoiceLabel.setText("Predicted human choice: " + predicted);
        computerChoiceLabel.setText("Therefore computer chooses: " + choice);
        winnerLabel.setText(winner);
    }

    public void updateStatistics(int humanWins, int compWins, int ties) {
        humanWinsLabel.setText("Human wins: " + humanWins);
        computerWinsLabel.setText("Computer wins: " + compWins);
        tiesLabel.setText("Ties: " + ties);
    }

    public void updateStatus(String status) {
        statusLabel.setText(status);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

