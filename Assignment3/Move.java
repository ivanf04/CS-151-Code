package Assignment3;

public enum Move {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    private final int value;

    Move(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Move fromInt(int input) {
        for (Move m : values()) {
            if (m.value == input) return m;
        }
        return null;
    }
}