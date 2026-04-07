package Assignment_5_gangOfFour;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI_Interface extends Application {

    // --- VIEW ELEMENTS ---
    private Stage primaryStage;
    private BorderPane root;
    private VBox gameLayout;
    private VBox setupLayout;
    
    // Game Screen Labels
    private Label roundLabel;
    private Label humanChoiceLabel;
    private Label predictedChoiceLabel;
    private Label computerChoiceLabel;
    private Label winnerLabel;
    private Label humanWinsLabel;
    private Label computerWinsLabel;
    private Label tiesLabel;
    private Label statusLabel; 

    // --- THE CONTROLLER ---
    private GameController controller;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        controller = new GameController(this);

        primaryStage.setTitle("Rock Paper Scissors Setup");

        // 1. Menu Bar Setup (Visible on both screens)
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

        // Bottom Status Bar (Visible on both screens)
        statusLabel = new Label("Waiting for setup...");
        statusLabel.setPadding(new Insets(5));
        statusLabel.setStyle("-fx-background-color: #e0e0e0;");
        statusLabel.setMaxWidth(Double.MAX_VALUE);

        // Build the two layouts
        buildSetupLayout();
        buildGameLayout();

        // Root Layout (BorderPane to hold Menu, Center Screen, and Bottom)
        root = new BorderPane();
        root.setTop(menuBar);
        root.setBottom(statusLabel);
        
        // Start by showing the setup screen
        root.setCenter(setupLayout);

        // Scene Setup
        Scene scene = new Scene(root, 450, 500);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * The Initial Setup Screen
     */
    private void buildSetupLayout() {
        setupLayout = new VBox(15);
        setupLayout.setAlignment(Pos.CENTER);
        setupLayout.setPadding(new Insets(20));

        Label titleLabel = new Label("Starting New Game:");
        titleLabel.setStyle("-fx-font-family: 'Helvetica'; -fx-font-size: 20px; -fx-font-weight: bold;");

        // Opponent Selection
        HBox opponentBox = new HBox(10);
        opponentBox.setAlignment(Pos.CENTER);
        Label opponentLabel = new Label("Select Opponent:");
        
        // Use ToggleButtons so only one can be clicked at a time
        ToggleButton mlBtn = new ToggleButton("ML");
        ToggleButton randomBtn = new ToggleButton("Random");
        ToggleGroup oppGroup = new ToggleGroup();
        mlBtn.setToggleGroup(oppGroup);
        randomBtn.setToggleGroup(oppGroup);
        mlBtn.setSelected(true); // Default selection
        
        opponentBox.getChildren().addAll(opponentLabel, mlBtn, randomBtn);

        // Number of Rounds Selection
        HBox roundsBox = new HBox(10);
        roundsBox.setAlignment(Pos.CENTER);
        Label roundsLabel = new Label("Number of rounds:");
        TextField roundsInput = new TextField("20"); // Default value
        roundsInput.setPrefWidth(50);
        roundsBox.getChildren().addAll(roundsLabel, roundsInput);

        // Start Game Button
        Button startBtn = new Button("Start GAME");
        startBtn.setStyle("-fx-font-weight: bold; -fx-padding: 10px 20px;");
        
        startBtn.setOnAction(e -> {
            try {
                int rounds = Integer.parseInt(roundsInput.getText().trim());
                if(rounds <= 0) throw new NumberFormatException();
                
                boolean isML = mlBtn.isSelected();
                controller.startGame(isML, rounds);
                
            } catch (NumberFormatException ex) {
                updateStatus("Error: Please enter a valid positive number for rounds.");
            }
        });

        setupLayout.getChildren().addAll(titleLabel, opponentBox, roundsBox, startBtn);
    }

    /**
     * Builds the Main Game Screen
     */
    private void buildGameLayout() {
        gameLayout = new VBox(10); 
        gameLayout.setAlignment(Pos.CENTER);
        gameLayout.setPadding(new Insets(20));

        roundLabel = new Label("Round: 1");
        roundLabel.setStyle("-fx-font-family: 'Helvetica'; -fx-font-size: 18px; -fx-font-weight: bold;");

        Label humanHeader = new Label("Human");
        humanHeader.setStyle("-fx-font-family: 'Helvetica'; -fx-font-size: 18px; -fx-font-weight: bold;");

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        Label chooseLabel = new Label("Choose:");
        Button rockBtn = new Button("Rock");
        Button paperBtn = new Button("Paper");
        Button scissorsBtn = new Button("Scissors");
        buttonBox.getChildren().addAll(chooseLabel, rockBtn, paperBtn, scissorsBtn);

        rockBtn.setOnAction(e -> controller.handlePlayerMove("Rock"));
        paperBtn.setOnAction(e -> controller.handlePlayerMove("Paper"));
        scissorsBtn.setOnAction(e -> controller.handlePlayerMove("Scissors"));

        humanChoiceLabel = new Label("Human chooses: --");

        Label computerHeader = new Label("Computer");
        computerHeader.setStyle("-fx-font-family: 'Helvetica'; -fx-font-size: 18px; -fx-font-weight: bold;");
        VBox.setMargin(computerHeader, new Insets(15, 0, 0, 0)); 

        predictedChoiceLabel = new Label("Predicted human choice: --");
        computerChoiceLabel = new Label("Therefore computer chooses: --");

        HBox winnerBox = new HBox(10);
        winnerBox.setAlignment(Pos.CENTER);
        Label winnerTitle = new Label("The winner:");
        winnerTitle.setStyle("-fx-font-family: 'Helvetica'; -fx-font-size: 18px; -fx-font-weight: bold;");
        winnerLabel = new Label("--");
        winnerBox.getChildren().addAll(winnerTitle, winnerLabel);
        VBox.setMargin(winnerBox, new Insets(15, 0, 15, 0));

        Label statsHeader = new Label("Statistics");
        statsHeader.setStyle("-fx-font-family: 'Helvetica'; -fx-font-size: 18px; -fx-font-weight: bold;");
        humanWinsLabel = new Label("Human wins: 0");
        computerWinsLabel = new Label("Computer wins: 0");
        tiesLabel = new Label("Ties: 0");

        gameLayout.getChildren().addAll(
                roundLabel, humanHeader, buttonBox, humanChoiceLabel,
                computerHeader, predictedChoiceLabel, computerChoiceLabel,
                winnerBox, statsHeader, humanWinsLabel, computerWinsLabel, tiesLabel
        );
    }

    // --- SCREEN NAVIGATION METHODS ---

    public void showSetupScreen() {
        primaryStage.setTitle("Rock Paper Scissors Setup");
        root.setCenter(setupLayout);
    }

    public void showGameScreen(int maxRounds) {
        primaryStage.setTitle("Rock Paper Scissors : " + maxRounds + " rounds/game");
        root.setCenter(gameLayout);
    }

    // --- DATA UPDATE METHODS ---

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
}