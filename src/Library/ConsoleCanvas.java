package Library;

public class ConsoleCanvas {
    private static char[][] canvas;
    private static int width;
    private static int height;

    public ConsoleCanvas(int width, int height, String studentId) {
        this.width = width + 2; // Add 2 for the left and right borders
        this.height = height + 2; // Add 2 for the top and bottom borders
        this.canvas = new char[this.height][this.width];

        for (int row = 0; row < this.height; row++) {
            for (int col = 0; col < this.width; col++) {
                if (row == 0 || row == this.height - 1 || col == 0 || col == this.width - 1) {
                    canvas[row][col] = '#'; // Set border
                } else {
                    canvas[row][col] = ' '; // Empty space within borders
                }
            }
        }

        // Place the student ID in the middle of the top border
        int startCol = (this.width - studentId.length()) / 2;
        for (int i = 0; i < studentId.length(); i++) {
            canvas[0][startCol + i] = studentId.charAt(i);
        }
    }

    // Method to place 'R' at a specific location within the canvas
    public static void showIt(int x, int y, char symbol) {
        if (x > 0 && x < width - 1 && y > 0 && y < height - 1) {
            canvas[y][x] = symbol;
        }
    }

    // Converts the canvas to a string representation
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (char[] row : canvas) {
            result.append(row).append('\n');
        }
        return result.toString();
    }

    public static void main(String[] args) {
        ConsoleCanvas c = new ConsoleCanvas(10, 5, "32007852"); // create a canvas
        c.showIt(4, 3, 'R'); // add a Robot at 4,3
        System.out.println(c.toString()); // display result
    }
}


    

