package Assignment_3_gangOfFour;


/**
 * Factory to handle Choice Algorithm selection
 */
public class ChoiceFactory {
   
    /**
     * 
     * @param rand true for random choice, flase for MLChoice 
     * @param moveHistory MoveHistory object for the game 
     * @return
     */
    public static ChoiceAlgorithm createChoice(boolean rand, MoveHistory moveHistory) {
        if(rand) {
            return new randomChoice();
        }
        else {
            return new MLChoice(moveHistory);
        }
    }
    
}
