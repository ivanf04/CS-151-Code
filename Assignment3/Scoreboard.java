package Assignment3;

public class Scoreboard {

    private int humanWins;
    private int computerWins;
    private int draws;

  //to be completed
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

    //TODO: create printWinner() function 

    public int getHumanWins(){
      return this.humanWins;
    }

    public int getComputerWins(){
      return computerWins;
    }

    public int getDraws(){
      return draws;
    }

    public void printScore(){
      System.out.printf("Score: Human:%d Computer:%d Draws:%d",
        this.humanWins, this.computerWins, this.draws);
      System.out.println();
    }

}