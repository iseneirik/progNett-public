class SortMonitor {
	// unsorted words
	String[] words;
	String outFile;

	// split amount
	int wordsPerThread;
	int carry;
	int numThreads;

	// String array that holds sorted words waiting for a merge
	String[] waitingWords;

	SortMonitor(String[] words, int numThreads, String outFile) {
		// init unsorted words, number of threads and filename for writing
		this.words = words;
		this.numThreads = numThreads;
		this.outFile = outFile;

		// calculate words per thread and the amount left in carry
		carry = words.length % numThreads;
		wordsPerThread = words.length / numThreads;

		// variable for report()
		waitingWords = null;
		
		// Create the initial numThread amount of threads
		createInitialThreads();		
	}

	private void createInitialThreads() {
		// every thread gets wordsPerThread words starting from index copyStart
		for (int i = 0, copyStart = 0; i < numThreads ; i++, copyStart += wordsPerThread) {
			if (i < carry) {
				// the first carry-amount of threads get one extra word
				InitSortThread sortThread = new InitSortThread(copyArray(copyStart, wordsPerThread+1), this);
				sortThread.start();
				copyStart++;
			} else {
				InitSortThread sortThread = new InitSortThread(copyArray(copyStart, wordsPerThread), this);
				sortThread.start();
			}
		}
	}

	private String[] copyArray(int startIndex, int amount) {
		// new array of strings
		String[] newArray = new String[amount];

		// init the new array
		for (int i = 0; i < amount; startIndex++, i++) {
			newArray[i] = words[startIndex];
		}

		return newArray;
	}

	public synchronized void report(String[] sortedWords) {
		// if the sortedWords length is as long as the original, it is done, else, merge
		if (sortedWords.length == words.length) {
			// make a new file with the sorted words
			new WordWriter(sortedWords, outFile);
		} else if (waitingWords == null) {
			// put the ready words in que for merging
			waitingWords = sortedWords;
		} else {
			// merge sortedWords with waitingWords
			MergeSortThread mergeThread = new MergeSortThread(waitingWords, sortedWords, this);
			mergeThread.start();
			waitingWords = null;
		}
	}
}