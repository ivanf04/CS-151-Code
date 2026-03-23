package Assignment_3_gangOfFour;

import java.util.*;

/**
 * Stores recent move history and sequence frequencies for the ML algorithm.
 * This class acts as the memory layer for machine learning.
 */
public class MoveHistory {

    private static final String DATA_FILE = "ml_frequency_data.txt";

    private final int n;
    private final Deque<Move> recentChoices; // last n - 1 choices
    private final Map<String, Integer> frequencyMap;

    /**
     * Default constructor uses n = 5.
     */
    public MoveHistory() {
        this(5);
    }

    /**
     * @param n sequence length used by the ML algorithm
     */
    public MoveHistory(int n) {
        this.n = n;
        this.recentChoices = new ArrayDeque<>();
        this.frequencyMap = new HashMap<>();
        loadData();
    }

    /**
     * Returns true if enough recent history exists to make a prediction.
     *
     * @return true if recentChoices has size n - 1
     */
    public boolean hasFullPrefix() {
        return recentChoices.size() == n - 1;
    }

    /**
     * Returns the current prefix formed by the last n - 1 recorded choices.
     *
     * @return a sequence string such as "RSPS"
     */
    public String buildCurrentPrefix() {
        StringBuilder builder = new StringBuilder();
        for (Move move : recentChoices) {
            builder.append(moveToChar(move));
        }
        return builder.toString();
    }

    /**
     * Returns how often the given full sequence has occurred.
     *
     * @param sequence the full sequence key
     * @return stored frequency, or 0 if not found
     */
    public int getFrequency(String sequence) {
        return frequencyMap.getOrDefault(sequence, 0);
    }

    /**
     * Records a completed round in the move history.
     *
     * This method first updates the sequence frequency map using the current
     * prefix plus the human player's move, then appends both the human and
     * computer moves into the recent history window.
     *
     * @param humanMove the move chosen by the human player
     * @param computerMove the move chosen by the computer player
     */
    public void recordRound(Move humanMove, Move computerMove) {
        updateSequenceCount(humanMove);
        appendChoice(humanMove);
        appendChoice(computerMove);
    }

    /**
     * Updates the frequency map for the completed sequence ending in the
     * human player's move.
     *
     * If enough recent history exists, this method forms:
     * current prefix + humanMove
     * and increments that sequence's stored count.
     *
     * @param humanMove the human player's move that completes the sequence
     */
    private void updateSequenceCount(Move humanMove) {
        if (recentChoices.size() == n - 1) {
            String sequence = getCurrentPrefix() + moveToChar(humanMove);
            frequencyMap.put(sequence, frequencyMap.getOrDefault(sequence, 0) + 1);
        }
    }

    /**
     * Appends one move to the recent history window.
     *
     * If the window is already full, the oldest move is removed first so that
     * only the most recent n - 1 choices are retained.
     *
     * @param move the move to append to recent history
     */
    private void appendChoice(Move move) {
        if (recentChoices.size() == n - 1) {
            recentChoices.removeFirst();
        }
        recentChoices.addLast(move);
    }

    /**
     * Saves the learned frequency data to a file so that it can be reused
     * in future games.
     */
    public void saveData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    /**
     * Loads previously saved frequency data from the data file.
     *
     * If the file does not exist yet, this method does nothing.
     */
    private void loadData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    frequencyMap.put(parts[0], Integer.parseInt(parts[1]));
                }
            }
        } catch (IOException e) {
            // Fine if the data file does not exist yet.
        }
    }

    /**
     * Converts a Move enum value to its single-character representation.
     *
     * @param move the move to convert
     * @return 'R' for ROCK, 'P' for PAPER, or 'S' for SCISSORS
     * @throws IllegalArgumentException if the move is invalid
     */
    private char moveToChar(Move move) {
        switch (move) {
            case ROCK:
                return 'R';
            case PAPER:
                return 'P';
            case SCISSORS:
                return 'S';
            default:
                throw new IllegalArgumentException("Invalid move.");
        }
    }
}
