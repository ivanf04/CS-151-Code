/*
  This class implements the functionality of a scoreboard. Instances of this class store the
  human wins, computer wins and draws of a game of rock paper scissors (RPS). The class has functoins
  to update the score, getter and setters, and a function to print the final outcome of a game of RPS. 
 */
package Assignment_3_gangOfFour;

public class Scoreboard {

    private int humanWins;
    private int computerWins;
    private int draws;

    public Scoreboard(){
      this.humanWins = 0;
      this.computerWins = 0;
      this.draws = 0;
    }

    public void humanWin() {
      this.humanWins++;
    }

    public void computerWin() {
      this.computerWins++;
    }

    public void draw() {
      this.draws++;
    }

    public int getHumanWins(){
      return this.humanWins;
    }

    public int getComputerWins(){
      return computerWins;
    }

    public int getDraws(){
      return draws;
    }

    /*
      This method is used after the result of a round of RPS is determined. Result is an ENUM type
      that represts which player won a speicific round. The switch statement determines which instance variable to increment. 
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

    /*
      function to display winner of a round, takes Result ENUM type as argument 
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

    /*
      Print the current score of the game, call this function after a game or after a round of RPS.
     */
    public void printScore(){
      System.out.printf("Score: Human:%d Computer:%d Draws:%d",
        this.humanWins, this.computerWins, this.draws);
      System.out.println();
    }

}