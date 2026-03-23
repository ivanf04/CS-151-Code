package Assignment_4_gangOfFour;

/**
* Implements the classic Rock-Paper-Scissors rules:
* Rock beats Scissors, Paper beats Rock, Scissors beats Paper.
*/
public class ClassicRPS implements Rules {

    /**
    * Applies classic Rock-Paper-Scissors rules to determine the winner.
    *
    * @param human the human player's move
    * @param computer the computer player's move
    * @return the round Result
    */
    @Override
    public Result determineOutcome(Move human, Move computer) {

        if (human == computer) return Result.DRAW;

        if (human == Move.ROCK && computer == Move.SCISSORS)
            return Result.HUMAN_WIN;

        if (human == Move.PAPER && computer == Move.ROCK)
            return Result.HUMAN_WIN;

        if (human == Move.SCISSORS && computer == Move.PAPER)
            return Result.HUMAN_WIN;

        return Result.COMPUTER_WIN;
    }
}
