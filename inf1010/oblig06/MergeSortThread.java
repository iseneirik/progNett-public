class MergeSortThread extends Thread {
	// vars for the sorted arrays and the new array they are to be merged in
	String[] words1;
	String[] words2;
	String[] sortedWords;

	// monitor to report to
	SortMonitor monitor;

	MergeSortThread(String[] words1, String[] words2, SortMonitor monitor) {
		// init vars
		this.words1 = words1;
		this.words2 = words2;
		this.monitor = monitor;
		sortedWords = new String[(words1.length + words2.length)];
	}

	public void run() {
		sort();
		monitor.report(sortedWords);
	}

	private void sort() {
		int index1 = 0;
		int index2 = 0;

		// mergesort
		for (int i = 0; i < sortedWords.length; i++) {
			if (index1 >= words1.length) {
				sortedWords[i] = words2[index2++];
			} else if (index2 >= words2.length) {
				sortedWords[i] = words1[index1++];
			} else if (words1[index1].compareTo(words2[index2]) < 0) {
				sortedWords[i] = words1[index1++];
			} else {
				sortedWords[i] = words2[index2++];
			}
		}
	}
}