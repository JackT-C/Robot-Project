package Library;

import java.util.ArrayList;
import java.util.Random;

public class RobotArena {
    private int width;
    private int height;
    private int maxRobots;
    private ArrayList<Robot> robots;
    private Random randomGenerator;

    public RobotArena(int width, int height) {
        this.width = width;
        this.height = height;
        this.robots = new ArrayList<>();
        this.randomGenerator = new Random();
    }

    public void addRobot() {
        int randomX, randomY;
        maxRobots = width * height;
        do {
            randomX = randomGenerator.nextInt(width);
            randomY = randomGenerator.nextInt(height);
        } while (getRobotAt(randomX, randomY) != null && robots.size() != maxRobots);

        // Assign a random direction to each robot
        Direction randomDirection = Direction.getRandomDirection();
        robots.add(new Robot(randomX, randomY, randomDirection));
    }

    public void addRobotAtPosition(int x, int y, Direction direction) {
        if (x >= 0 && x < width && y >= 0 && y < height && getRobotAt(x, y) == null) {
            robots.add(new Robot(x, y, direction));
        } else {
            System.out.println("Cannot place robot at (" + x + ", " + y + "): Position is invalid or already occupied.");
        }
    }
    // Check if a position is within the arena bounds and has no robot
    public boolean canMoveHere(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return false; // Position is out of bounds
        }
        return getRobotAt(x, y) == null; // Check if there's no robot at this position
    }

    // Move all robots by calling tryToMove on each
    public void moveAllRobots() {
        for (Robot robot : robots) {
            robot.tryToMove(this); // Pass the arena reference to each robot
        }
    }


    public void showRobots(ConsoleCanvas c) {
        for (Robot robot : robots) {
            robot.displayRobot(c);
        }
    }
    public ArrayList<Robot> getRobots() {
        return robots;
    }

    public Robot getRobotAt(int x, int y) {
        for (Robot robot : robots) {
            if (robot.isHere(x, y)) {
                return robot;
            }
        }
        return null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Arena " + width + " by " + height + " with robots:\n");
        for (Robot robot : robots) {
            result.append(robot.toString()).append("\n");
        }
        return result.toString();
    }
}




