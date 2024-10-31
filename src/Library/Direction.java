package Library;

import java.util.Random;

public enum Direction {
    NORTH, EAST, SOUTH, WEST;

    private static final Direction[] VALUES = values();
    private static final int SIZE = VALUES.length;
    private static final Random RANDOM = new Random();

    // Method to get a random direction
    public static Direction getRandomDirection() {
        return VALUES[RANDOM.nextInt(SIZE)];
    }

    // Using the next ordinal value to go around the directions clockwise for the next direction
    public Direction next() {
        return VALUES[(this.ordinal() + 1) % SIZE];
    }

    // Testing
    public static void main(String[] args) {
        System.out.println("Random Direction: " + getRandomDirection());
        System.out.println("Next Direction: " + getRandomDirection().next());
    }
}

