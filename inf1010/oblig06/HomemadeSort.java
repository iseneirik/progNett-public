class HomemadeSort {
	// vars from program parameters
	int numThreads;
	String inFile;
	String outFile;

	// array to hold unsorted words
	String[] words;

	HomemadeSort(int numThreads, String inFile, String outFile) {
		// init vars
		this.numThreads = numThreads;
		this.inFile = inFile;
		this.outFile = outFile;

		// read words from file
		readWords();
		// sort words and write to file
		sortWords();
	}

	private void readWords() {
		// Read words into array
		WordReader wordReader = new WordReader(inFile);
		words = wordReader.getWords();
	}

	private void sortWords() {
		// Start the sorting
		new SortMonitor(words, numThreads, outFile);
	}

}

