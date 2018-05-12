import java.io.*;

class SudokuContainer {
	// Maximum number of solutions that are to be stored
	private final int MAX_SOLUTIONS = 750;
	private int numSolutions;
	private Solution first;

	SudokuContainer() {
		numSolutions = 0;
		first = null;
	} // end Constructor


	public boolean insert(Board board) {
		if (numSolutions < MAX_SOLUTIONS) {
			// Make a new solution
			Solution newSolution = new Solution(board);
			
			// Put new solution first
			newSolution.next = first;
			first = newSolution;

			// Update numSolutions, return true
			numSolutions++;
			return true;
		} else {
			// Update numSolutions, return false
			numSolutions++;
			return false;
		}
	}

	public Solution get() {
		return first;
	} // end get()

	public int getSolutionCount() {
		return numSolutions;
	} // end getSolutionCount()

	public int getMaxSolutionsStored() {
		return MAX_SOLUTIONS;
	}

	public void printToScreen() {
		// if more than 3 solutions, use minimal version
		boolean minimal = (numSolutions > 3 ? true : false);

		// Start with the first solution
		Solution currSolution = get();

		// Print Solution until numSolutions or MAX_SOLUTIONS is reached
		for (int i = 0; i < numSolutions && i < MAX_SOLUTIONS; i++) {
			System.out.printf("%3d: %s%n", (i+1), currSolution.getString(minimal));
			currSolution = currSolution.next;	
		}
	} // end printToScreen()

	public void printToFile(String filename) {
		PrintWriter writer = null;
		try {
			// open up a writer
			writer = new PrintWriter(filename, "UTF-8");
			
			// if more than 3 solutions, use minimal version
			boolean minimal = (numSolutions > 3 ? true : false);

			// Start with the first solution
			Solution currSolution = get();

			// Print Solution until numSolutions or MAX_SOLUTIONS is reached
			for (int i = 0; i < numSolutions && i < MAX_SOLUTIONS; i++) {
				writer.printf("%3d: %s%n", (i+1), currSolution.getString(minimal));
				currSolution = currSolution.next;	
			}
		} catch (IOException e) {
			// Alert user in case of failure
			System.out.println("Something happened while writing to file...");
		} finally {
			// close the PrintWriter
			writer.close();
		}
	} // end printToFile()
} // end SudokuContainer