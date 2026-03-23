package Assignment_3_gangOfFour;
import java.util.*;


/**
 * Machine learning algorithm that predicts the human player's next move
 * using previously recorded move sequences.
 */
public class MLChoice implements ChoiceAlgorithm {

    private static final String DATA_FILE = "ml_frequency_data.txt";

    private final int n;
    private final Deque<Move> recentChoices; // stores the most recent n - 1 choices
    private final Map<String, Integer> recordedChoices; 
    private final randomChoice fallbackChoice; // if not enough moves to learn from

    /**
     * Default constructor sets n = 5.
     */
    public MLChoice() {
        this(5);
    }

    /**
     * @param numRecChoices size of the stored sequence window
     */
    public MLChoice(int numRecChoices) {
        this.n = numRecChoices;
        this.recentChoices = new ArrayDeque<>();
        this.recordedChoices = new HashMap<>();
        this.fallbackChoice = new randomChoice();
        loadData();
    }

    /**
     * Determines the computer's next move by predicting the human player's
     * next move from the last n - 1 recorded choices. If no pattern exists,
     * returns a random move.
     *
     * @return the move chosen by the ML algorithm
     */
    @Override
    public Move determineMove() {
        if (recentChoices.size() < n - 1) {
            return fallbackChoice.determineMove();
        }

        String prefix = dequeToString(recentChoices);

        int rockCount = recordedChoices.getOrDefault(prefix + moveToChar(Move.ROCK), 0);
        int paperCount = recordedChoices.getOrDefault(prefix + moveToChar(Move.PAPER), 0);
        int scissorsCount = recordedChoices.getOrDefault(prefix + moveToChar(Move.SCISSORS), 0);

        if (rockCount == 0 && paperCount == 0 && scissorsCount == 0) {
            return fallbackChoice.determineMove();
        }

        Move predictedHumanMove = Move.ROCK;
        int maxCount = rockCount;

        if (paperCount > maxCount) {
            predictedHumanMove = Move.PAPER;
            maxCount = paperCount;
        }

        if (scissorsCount > maxCount) {
            predictedHumanMove = Move.SCISSORS;
        }

        return counterMove(predictedHumanMove);
    }

    /**
     * Records the completed round.
     *
     * The assignment's stored frequencies are sequences of length n that end
     * with a human move. So we:
     * 1. Use the current n - 1 recent choices plus the new human move to update
     *    the frequency map.
     * 2. Then append the human move and the computer move to the recent history
     *    for use in future predictions.
     *
     * @param humanMove the human player's move
     * @param computerMove the computer player's move
     */
    @Override
    public void recordRound(Move humanMove, Move computerMove) {
        if (recentChoices.size() == n - 1) {
            String sequence = dequeToString(recentChoices) + moveToChar(humanMove);
            recordedChoices.put(sequence, recordedChoices.getOrDefault(sequence, 0) + 1);
        }

        addRecentChoice(humanMove);
        addRecentChoice(computerMove);
    }

    /**
     * Saves learned frequency data so future games can reuse it.
     */

    //TODO: MAKE THIS PART OF NEW DATA STORAGE CLASS
    @Override
    public void saveData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (Map.Entry<String, Integer> entry : recordedChoices.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Could not save ML data: " + e.getMessage());
        }
    }

    /**
     * Loads previously saved frequency data.
     */

    //TODO: MAKE THIS PART OF NEW DATA STORAGE CLASS
    private void loadData() {
    }

    /**
     * Adds a choice to the recent history and keeps only the last n - 1 choices.
     *
     * @param move the move to add
     */
    private void addRecentChoice(Move move) {
        if (recentChoices.size() == n - 1) {
            recentChoices.removeFirst();
        }
        recentChoices.addLast(move);
    }

    /**
     * Converts the recent choice deque to a string of the move sequence such as "RSPS".
     *
     * @param deque the recent choice deque
     * @return the string form of the deque
     */
    private String dequeToString(Deque<Move> deque) {
        StringBuilder builder = new StringBuilder();
        for (Move move : deque) {
            builder.append(moveToChar(move));
        }
        return builder.toString();
    }

    /**
     * Converts a move to its single-character form.
     *
     * @param move the move to convert
     * @return R, P, or S
     */
    private char moveToChar(Move move) {
        switch (move) {
            case ROCK:
                return 'R';
            case PAPER:
                return 'P';
            case SCISSORS:
                return 'S';
        }
    }

    /**
     * Returns the move that beats the predicted human move.
     *
     * @param predictedHumanMove the predicted human move
     * @return the counter-move
     */
    private Move counterMove(Move predictedHumanMove) {
        switch (predictedHumanMove) {
            case ROCK:
                return Move.PAPER;
            case PAPER:
                return Move.SCISSORS;
            case SCISSORS:
                return Move.ROCK;
        }
    }
}
