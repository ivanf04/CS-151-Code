package Assignment_3_gangOfFour;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class acts as a data base to track round sequences 
 */
public class RoundRecord {
    private int n;
    private Deque<Move> history;
    private Map<List<Move>, Integer> recordedChoices;

    public RoundRecord() {
        this.n = 5; //default size of 5
        this.history = new ArrayDeque<>();
        this.recordedChoices = new HashMap<>();
    }

    public RoundRecord(int num) {
        this.n = num; //custom size of num
        this.history = new ArrayDeque<>();
        this.recordedChoices = new HashMap<>();
    }

    /**
     * Method to check if the curent n elements have been recorded or not 
     */
    private void checkMap() {
        //checl if deque is large enough
        if (history.size() >= n) {
            List<Move> tempList = List.copyOf(history);
            if (recordedChoices.containsKey(tempList)) {
                recordedChoices.put(tempList, recordedChoices.get(tempList) + 1);
            } 
            else {
                recordedChoices.put(tempList, 1);
            }
        }
    }

    /** 
     * method to add a move to the history deque 
     * call checkmap after every add to reference the DB 
     * @param move the lastest move played in a Game of RPS 
    */
    public void addMove(Move move) {
        //TODO: throw exception when deque gets too large
        if(history.size() == n) {
            history.removeFirst();
        }
        history.addLast(move);
        checkMap();
    }

    /**
     * Runs .contians method on the map
     * @param list of N moves
     * @return True if map contains the sequence, else false 
     */
    public boolean checkDB(List<Move> list) {
        if(recordedChoices.containsKey(list)) {
            return true;
        }
        return false;
    }
}


