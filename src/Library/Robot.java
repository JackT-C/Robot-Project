package Library;


public class Robot {
    private int x;
    private int y;
    private int id;
    private Direction direction;
    private static int idCounter = 0;

    public Robot(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.id = idCounter++;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }
    // Method to try moving the robot in its current direction
    public void tryToMove(RobotArena arena) {
        int newX = x;
        int newY = y;

        // Calculate the new position based on direction
        switch (direction) {
            case NORTH -> newY -= 1;
            case EAST -> newX += 1;
            case SOUTH -> newY += 1;
            case WEST -> newX -= 1;
        }

        // Check if the robot can move to the new position
        if (arena.canMoveHere(newX, newY)) {
            // Move to the new position
            x = newX;
            y = newY;
        } else {
            // Change direction to the next one
            direction = direction.next();
        }
    }

    public boolean isHere(int sx, int sy) {
        return this.x == sx && this.y == sy;
    }
    public void displayRobot(ConsoleCanvas c){
        ConsoleCanvas.showIt(x+ 1,y+ 1, 'R');
    }

    @Override
    public String toString() {
        return "Robot " + id + " is at " + x + ", " + y + " facing " + direction.toString();
    }
}
