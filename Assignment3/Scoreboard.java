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

    public void humanWin(){
      this.humanWins++;
    }

    public void computerWin(){
      this.computerWins++;
    }

    public int getHumanWins(){
      return this.humanWins;
    }

    public int getComputerWins(){
      return computerWins;
    }

    public int getDraws(){
      retutn draws;
    }

    public void toString(){
      System.out.printf("Score: Human:%d Computer:%d Draws:%d",
        this.humanWins, this.computerWins, this.draws);
      System.out.println();
    }

}
