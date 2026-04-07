package Assignment_4_gangOfFour;

/**
* Defines a rule set for determining the outcome of a round
* given a human move and a computer move.
*/
public interface Rules {

  /**
  * Determines the outcome of a round based on the two moves.
  *
  * @param human the human player's move
  * @param computer the computer player's move
  * @return the Result of the round (HUMAN_WIN, COMPUTER_WIN, or DRAW)
  */
  Result determineOutcome(Move human, Move computer);

}
