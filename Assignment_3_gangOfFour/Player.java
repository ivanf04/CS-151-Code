package Assignment_3_gangOfFour;

/**
* Represents a player in the game who has a name and can choose a move.
*/
public interface Player {
    
    /**
    * Returns the player's display name.
    *
    * @return the player's name
    */
    String getName();

    /**
    * Obtains the player's move for the current round.
    *
    * @return the Move chosen by the player (never null for valid implementations)
    */
    Move getMove();
}
