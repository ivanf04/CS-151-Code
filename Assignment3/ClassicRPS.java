package Assignment3;
public class ClassicRPS implements Rules {

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
