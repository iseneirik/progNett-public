import java.util.Scanner;
import java.io.*;

class SudokuReader {
	// Scanner for reading file
	private Scanner scanner;

	// Variables to hold info
	private String filename;
	private int boxHeight;
	private int boxLength;
	private int boardSize;
	private Board problem;

	public SudokuReader(String filename) {
		this.filename = filename;
		readProblem();
	} // end Constructor

	private void readProblem() {
		try {
			// init scanner
			scanner = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			// Shut down program if file not found
			System.out.println("File not found! Please try again!");
			System.exit(1);
		}

		// Read in size, then go to next line
		boxHeight = scanner.nextInt();
		boxLength = scanner.nextInt();
		boardSize = boxHeight * boxLength;
		scanner.nextLine();

		// Create a board to store the problem
		problem = new Board(boxHeight, boxLength);

		// Read in each line and make squares
		for (int yPos = 0; yPos < boardSize; yPos++) {
			String line[] = scanner.nextLine().split("(?!^)");
	

			// Fill the board with the problem
			for (int xPos = 0; xPos < boardSize; xPos++) {
				if (line[xPos].equals(".")) {
					problem.putSquare(xPos, yPos, new UndefinedSquare(0));
				} else {
					problem.putSquare(xPos, yPos, new PredefinedSquare(Integer.valueOf(line[xPos], 36)));
				}
			}
		}		
	} // end readProblem()

	public Board getProblem() {
		return problem;
	} // end getProblem

} // end SudokuReader