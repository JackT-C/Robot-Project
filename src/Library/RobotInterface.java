package Library;


import javax.swing.*;
import java.io.*;
import java.util.List;
import java.util.Scanner;

public class RobotInterface {

    private Scanner s;								// scanner used for input from user
    private RobotArena myArena;				// arena in which Robots are shown
    /**
     * constructor for RobotInterface
     * sets up scanner used for input and the arena
     * then has main loop allowing user to enter commands
     */
    public RobotInterface() {
        s = new Scanner(System.in);			// set up scanner for user input
        myArena = new RobotArena(20, 6);	// create arena of size 20*6

        char ch = ' ';
        do {
            System.out.print("Enter (A)dd Robot, get (I)nformation, (M)ove all Robots once, (T)en moves (Animated), create (N)ew arena, (S)ave arena, (L)oad arena, or e(X)it > ");
            ch = s.next().charAt(0);
            s.nextLine();
            switch (ch) {
                case 'S':
                case 's':
                    saveArena();
                    break;
                case 'L':
                case 'l':
                    loadArena();
                    break;
                case 'N':
                case 'n':
                    createNewArena();
                    break;
                case 'A' :
                case 'a' :
                    myArena.addRobot();	// add a new Robot to arena
                    break;
                case 'I' :
                case 'i' :
                    System.out.print(myArena.toString());
                    break;
                case 'x' : 	ch = 'X';
                    System.exit(0);// when X detected program ends
                    break;
                case 'T':
                case 't':
                    moveAndDisplayTenTimes();
                    break;
                case 'm':
                    myArena.moveAllRobots(); // Move all robots once
                    System.out.print(myArena.toString());
                    break;
                case 'D':
                case 'd':
                    doDisplay();
            }
        } while (true);
    }

    public void saveArena() {
        System.out.print("Enter the filename to save the arena (with .txt extension): ");
        String filename = s.nextLine(); // Read the filename from console

        try (FileWriter writer = new FileWriter(filename)) {
            // Write arena dimensions
            writer.write("Arena " + myArena.getWidth() + " " + myArena.getHeight() + "\n");

            // Write each robot's position and direction
            List<Robot> robots = myArena.getRobots(); // Ensure you have a method to get the robots
            for (Robot robot : robots) {
                writer.write(robot.getX() + " " + robot.getY() + " " + robot.getDirection() + "\n");
            }

            System.out.println("Arena saved to " + filename);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the file.");
            e.printStackTrace();
        }
    }



    public void loadArena() {
        System.out.print("Enter the filename to load the arena from (with .txt extension): ");
        String filename = s.nextLine(); // Read the filename from console

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();
            if (line != null) {
                String[] dimensions = line.split(" ");
                int width = Integer.parseInt(dimensions[1]);
                int height = Integer.parseInt(dimensions[2]);

                myArena = new RobotArena(width, height); // Create new arena
                System.out.println("Loaded arena with size " + width + " x " + height);

                // Read robots
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(" ");
                    int x = Integer.parseInt(parts[0]);
                    int y = Integer.parseInt(parts[1]);
                    Direction direction = Direction.valueOf(parts[2]); // Assuming direction is stored as string

                    myArena.addRobotAtPosition(x, y, direction); // Add robot to arena
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading the file.");
            e.printStackTrace();
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Error reading the arena dimensions or robot data.");
            e.printStackTrace();
        }
    }


    void doDisplay() {
        int canvasWidth = myArena.getWidth();
        int canvasHeight = myArena.getHeight();
        ConsoleCanvas canvas = new ConsoleCanvas(canvasWidth, canvasHeight, "32007852");

        myArena.showRobots(canvas);
        System.out.println(canvas.toString());
    }
    private void moveAndDisplayTenTimes() {
        for (int i = 0; i < 10; i++) {
            ConsoleCanvas canvas = new ConsoleCanvas(myArena.getWidth(), myArena.getHeight(), "32007852");
            myArena.moveAllRobots();
            myArena.showRobots(canvas);  // Draw robots on canvas
            System.out.println(canvas.toString()); // Display the updated arena

            try {
                Thread.sleep(250); // 250ms delay between moves
            } catch (InterruptedException e) {
                System.out.println("Error in delaying the move: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }
    private void createNewArena(){
        System.out.println("New arena Width: ");
        int arenaWidth = s.nextInt();
        System.out.println("New arena Height: ");
        int arenaHeight = s.nextInt();
        if (arenaWidth >= 8 && arenaHeight >= 6){
            myArena = new RobotArena(arenaHeight, (arenaWidth));
            System.out.println("New arena Created with size "+arenaWidth+"X" +arenaHeight);
        }
        else {
            System.out.println("Please input a Width >= 8, and height >= 6 to ensure student number fits.");
        }
    }

    public static void main(String[] args) {
        RobotInterface r = new RobotInterface();	// just call the interface
    }
}