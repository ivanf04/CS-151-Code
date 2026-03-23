package Assignment_3_gangOfFour;
import java.util.*;


/**
 * Machine learning algorithm to intelligently deterime the next move 
 */
public class MLChoice implements ChoiceAlgrorithm {

    private int n;
    private Deque<Move> history;
    private Map<List<Move>, Integer> recordedChoices;
    private ChoiceAlgrorithm random = new randomChoice();

    /**
     * default constructor sets n = 5
     */
    public MLChoice() {
        this.n = 5;
        this.history = new ArrayDeque<>();
        this.recordedChoices = new HashMap<>();
    }

    /**
     * 
     * @param numRecChoices sets the size of recorded sequences 
     */
    public MLChoice(int numRecChoices) {
        this.n = numRecChoices;
        this.history = new ArrayDeque<>();
        this.recordedChoices = new HashMap<>();
    }

    /**
     * Enqueues every input a game and deques when queue is of length n
     * check the hashmap after a move is added
     * 
     * @param move is the latest move in a game of RPS
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

        if (history.size() < n){
            return random.determineMove();
        }

        int maxFequency = 0; 
        List<Move> predictedMoves = new ArrayList<>();
        /**
         * TODO: implement machine learing algorithm 
         * Check the current squence against the existing records, based off the last 
         * opposing player choice choose the winning move
         */
        
        //take the first four inputs in the queue, 
        //match them with the corresponind sequences in the map
        //take the one with the highest frequency and take the last Move from that sequence
        //as the move to be played 
        List<Move> lastFour = new ArrayList<>(history);
        Iterator<Move> it  = history.descendingIterator();

        for(int i = 0; i < n - 1 && it.hasNext(); i++){
            lastFour.add(it.next());
        }
        Collections.reverse(lastFour);

        for(List<Move> key : recordedChoices.keySet()) {
            if(key.subList(0, n - 1).equals(lastFour)) {
                //match found, update max frequency and predictedMoves
                if(recordedChoices.get(key) >= maxFequency) {
                    maxFequency = recordedChoices.get(key);
                    predictedMoves.addAll(key); 
                }
            }
        }

        Move predicMove = predictedMoves.get(n - 1); 
        return predicMove;
    }
}
