class InitSortThread extends Thread {
	// vars for words to sort and monitor to report to
	String[] words;
	SortMonitor monitor;

	InitSortThread(String[] words, SortMonitor monitor) {
		// init vars
		this.words = words;
		this.monitor = monitor;
	}

	public void run() {
		sort();
		monitor.report(words);
	}

	private void sort() {
		// simple but unefficient sorting algorithm
		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < words.length; j++) {
				if (words[j].compareTo(words[i]) > 0) {
					String sub = words[j];
					words[j] = words[i];
					words[i] = sub;
				}
			}
		}
	}
}