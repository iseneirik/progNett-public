class Solution {
	// Save numbers for printing solution
	private int[][] numArray;
	
	// Pointer to the next solution
	public Solution next;

	// int to save boardSize
	private int boardSize;


	Solution(Board board) {
		// init boardSize and numArray
		boardSize = board.getSize();
		numArray = new int[boardSize][boardSize];

		// Copy numbers from board to numArray
		for (int yPos = 0; yPos < boardSize; yPos++) {
			for (int xPos = 0; xPos < boardSize; xPos++) {
				numArray[yPos][xPos] = board.getSquare(xPos, yPos).getValue();
			}
		}
	} // end Constructor

	public int[][] getNumArray() {
		return numArray;
	} // end getNumArray()

	public int getSize() {
		return boardSize;
	} // end getSize()

	public String getString(boolean minimal) {
		// non-minimal version needs two newlines to look clean
		String solutionString = (minimal ? "" : "\n\n");

		for (int yPos = 0; yPos < numArray[0].length; yPos++) {
			for (int xPos = 0; xPos < numArray[0].length; xPos++) {
				// Prints out every Row
				solutionString += Integer.toString(numArray[yPos][xPos], 36).toUpperCase();
			}
			// Splits rows with either a newline or '//'
			if (minimal) { solutionString += "//"; }
			else 		 { solutionString += "\n"; }
		}

		return solutionString;
	} // end getString()
} // end Solution