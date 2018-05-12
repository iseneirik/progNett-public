class Oblig5 {
	public static void main(String[] args) {
		SudokuReader sudokuReader;
		SudokuContainer container;
		Board problem;

		// variable for timing solve
		float duration;

		try {
			if (args.length > 0) {
				// Create a reader and a container for solutions
				sudokuReader = new SudokuReader(args[0]);
				container = new SudokuContainer();
				
				// Get problem and set up squares and container
				problem = sudokuReader.getProblem();
				problem.setContainer(container);
				problem.linkSquares();


				long startTime = System.nanoTime();
				
				// Start solving
				problem.solve();
				
				long endTime = System.nanoTime();
				duration = (endTime - startTime) / 1_000_000.0f;
				
			} else {
				throw new InvalidSyntaxException();
			}
		} catch (InvalidSyntaxException e) {
			System.out.println("Correct syntax: java Oblig5 <problem filename> [<solutions filename>]");
			return;
		}


		if (args.length > 1) {
			container.printToFile(args[1]);
		} else {
			container.printToScreen();
		}

		System.out.println("ms: " + duration);
	} // end main()

} // end Oblig5

class InvalidSyntaxException extends Exception {
	
} // end InvalidSyntaxException