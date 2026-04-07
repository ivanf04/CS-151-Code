package Assignment_5_gangOfFour;

/**
* Acts as a scoreboard, tracking the running totals of human wins, computer wins, and draws.
*/
public class Scoreboard {

    private int humanWins;
    private int computerWins;
    private int draws;

    /**
    * Constructs a Scoreboard with all counters initialized to zero.
    */
    public Scoreboard(){
      this.humanWins = 0;
      this.computerWins = 0;
      this.draws = 0;
    }

    /**
    * Increments the human win counter by 1.
    */
    public void humanWin() {
      this.humanWins++;
    }

    /**
    * Increments the computer win counter by 1.
    */
    public void computerWin() {
      this.computerWins++;
    }

    /**
    * Increments the draw counter by 1.
    */
    public void draw() {
      this.draws++;
    }

    /**
    * Returns the number of human wins.
    *
    * @return total human wins
    */
    public int getHumanWins(){
      return this.humanWins;
    }

    /**
    * Returns the number of computer wins.
    *
    * @return total computer wins
    */
    public int getComputerWins(){
      return computerWins;
    }

    /**
    * Returns the number of draws.
    *
    * @return total draws
    */
    public int getDraws(){
      return draws;
    }

    /**
    * Updates the scoreboard based on the result of one round.
    *
    * @param result the Result of the completed round
    */
    public void updateScore(Result result) {
        switch (result) {
          case HUMAN_WIN:
            humanWin();
            break;
          case COMPUTER_WIN:
            computerWin();
            break;
          default:
            draw();
        }

    }

    /**
    * Displays a message indicating the winner of the current round.
    *
    * @param result the Result of the round (HUMAN_WIN, COMPUTER_WIN, or DRAW)
    */
    public void displayRoundWinner(Result result) {
        switch(result){
            case HUMAN_WIN:
                System.out.print("You Win!");
                break;
            case COMPUTER_WIN:
                System.out.print("Computer Wins!");
                break;
            default:
                System.out.print("Draw!");
        }
    }

    /**
    * Prints the current score totals to the console.
    */
    public void printScore(){
      System.out.printf("Score: Human:%d Computer:%d Draws:%d",
        this.humanWins, this.computerWins, this.draws);
      System.out.println();
    }

}
