class Sort {
	public static void main(String[] args) {
		// vars for program parameters
		int numThreads;
		String inFile;
		String outFile;

		try {
			if (args.length > 2) {
				// init vars
				numThreads = Integer.parseInt(args[0]); 
				inFile = args[1];
				outFile = args[2];
			} else {
				throw new InvalidSyntaxException();
			}
		} catch (InvalidSyntaxException e) {
			System.out.println("Correct syntax: java Oblig6 <number of threads> <names.txt> <out.txt>");
			return;
		}

		// Start the sorting
		HomemadeSort sortedWords = new HomemadeSort(numThreads, inFile, outFile);
	}
}

class InvalidSyntaxException extends Exception {
	
} // end InvalidSyntaxException