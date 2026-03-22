package Assignment_3_gangOfFour;
import java.util.*;


/**
 * Machine learning algorithm to intelligently deterime the next move 
 */
public class MLChoice implements ChoiceAlgrorithm {

    private int n;
    private Deque<Move> history;
    private Map<List<Move>, Integer> recordedChoices;

    public MLChoice() {
        this.n = 5;
        this.history = new ArrayDeque<>();
        this.recordedChoices = new HashMap<>();
    }

    public MLChoice(int numRecChoices) {
        this.n = numRecChoices;
        this.history = new ArrayDeque<>();
        this.recordedChoices = new HashMap<>();
    }

    /**
     * Enqueues every input a game and deques when queue is of length n
     * check the hashmap after a move is added
     */
    public void addMove(Move move) {
        if(history.size() == n) {
            history.removeFirst();
        }
        history.addLast(move);
        checkMap();
    }

    /**
     * copy the curent Deque to a list and then check the HashMap
     * if the sequence has already been recorded, increment frequency
     * else create new K,V pair 
     */
    private void checkMap() {
        List<Move> curentSequence = List.copyOf(history);
        if(recordedChoices.containsKey(curentSequence)) {
            recordedChoices.put(curentSequence, recordedChoices.get(curentSequence) + 1);
        }
        else {
            recordedChoices.put(curentSequence, 1);
        }
    }

    @Override
    public Move determineMove() {
        //TODO: implement machine learing algorithm 
        return Move.fromInt(0);
    }
}
