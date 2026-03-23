package Assignment_3_gangOfFour;

public class ChoiceFactory {
   
    public static ChoiceAlgorithm createChoice(boolean rand, MoveHistory moveHistory) {
        if(rand) {
            return new randomChoice();
        }
        else {
            return new MLChoice(moveHistory);
        }
    }
    
}
