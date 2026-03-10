package Assignment_3_gangOfFour;
import java.util.Random;

public class ComputerPlayer implements Player {

    private final String name;
    private final Random random = new Random();

    public ComputerPlayer(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Move getMove() {
        int choice = random.nextInt(3) + 1;
        return Move.fromInt(choice);
    }
}