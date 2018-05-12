import java.util.*;
import java.io.*;

class Dictionary {
	// The file that holds the dictionary words
	private final String FILENAME;

	// Scanner to read the words, and BinSearchTree to hold them
	private Scanner wordReader;
	private BinSearchTree dictionary;

	// PrintWriter to write statistics and searches for a session
	private PrintWriter writer;

	// Initialize FILENAME and start the reading method
	public Dictionary(String fileName) {
		FILENAME = fileName;
		dictionary = new BinSearchTree();
		readWords();

		printStatistics();
	} // end Constructor()

	public void printStatistics() {
		// Hold depth, number of nodes and nodes in each depth
		int depth = dictionary.getDepth();
		int numNodes = dictionary.getNumNodes();
		int[] nodesByDepth = dictionary.getNodesByDepth();

		// Calculate the average depth (where root has depth 0)
		long totalDepth = 0;
		for (int i = 0; i < nodesByDepth.length; i++) {
			totalDepth += (i*nodesByDepth[i]);		
		}		
		double avgDepth = totalDepth/(float)numNodes;

		try {
			// open up a writer to file "statistics.txt"
			writer = new PrintWriter("statistics.txt", "UTF-8");

			// Print statistics to file
			writer.printf("----- Statistics -----\n");
			writer.printf("Depth of tree: %d\n", depth);
			writer.printf("Nodes per depth: \n");
			for (int i = 0; i < nodesByDepth.length; i++) {
				writer.printf("%2d: %4d nodes\n", i, nodesByDepth[i]);
			}
			writer.printf("Average depth: %.2f\n", avgDepth);
			writer.printf("First word: %s\n", dictionary.getFirstWord());
			writer.printf("Last word: %s\n", dictionary.getLastWord());
			writer.printf("----------------------\n\n");

		} catch (IOException e) {
			// Alert user in case of failure
			System.out.println("Something happened while writing to file...");
		} finally {
			writer.close();
		}
	} // end printStatistics()

	private void readWords() {
		try {
			// Open a reading stream to the file
			wordReader = new Scanner(new File(FILENAME));
			while (wordReader.hasNext()) {
				// Keep reading words to end of file
				dictionary.insertWord(wordReader.next());
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found, please try again!");
			System.exit(1);
		}
	} // end readWords()


	public void lookUp(String word, boolean toFile) {
		// Prints if the word is found, if not, looks for similar words
		if (dictionary.containsWord(word)) {
			System.out.printf("\"%s\" was found in the dictionary!\n\n", word);
		} else {
			System.out.printf("\"%s\" was not found, generating suggestions...\n", word);
			
			// Track time used to generate similar words
			long startTime = System.nanoTime();
			
			// WordScrambler generates similar words
			WordScrambler similarWords = new WordScrambler(word);
			String[] newWords = similarWords.getSimilarWords();

			// Search for all words in newWords[], save all hits
			List<String> wordSuggestion = new LinkedList<String>();
			for (String s : newWords) {
				if (dictionary.containsWord(s) && !wordSuggestion.contains(s)) {
					wordSuggestion.add(s);
				}
			}

			// Time when word generation and search is over
			long endTime = System.nanoTime();

			// divide by 1000000 for milliseconds
			long duration = (endTime - startTime);

			System.out.printf("\nTime used: %.3f ms\nHits found: %d\n\n",
							  	duration / 1000000.0, wordSuggestion.size());

			// If any suggestions where found, print them
			if (wordSuggestion.size() > 0) {
				System.out.println("Did you mean:");
				for (String s : wordSuggestion) {
					System.out.println(s);
				}
				System.out.println();
			} else {
				System.out.println("No similar words where found!\n");
			}
		}
		System.out.println("------------------------------");
	} // end lookUp()

	public void deleteWord(String word) {
		System.out.printf("Deleting \"%s\"\n", word);
		dictionary.deleteWord(word);
	} // end deleteWord()

	public void insertWord(String word) {
		System.out.printf("Inserting \"%s\"\n", word);
		dictionary.insertWord(word);
	} // end insertWord()
} // end Dictionary