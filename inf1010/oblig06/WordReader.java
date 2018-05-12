import java.util.*;
import java.io.*;

class WordReader {
	// vars for reading
	Scanner scanner;
	String fileName;

	// vars for storage
	int numWords;
	String[] words;

	WordReader(String fileName) {
		try {
			// init scanner and read file
			this.fileName = fileName;
			scanner = new Scanner(new File(fileName));
			readFile();
		} catch (FileNotFoundException e) {
			// Shut down program if file not found
			System.out.println("File not found! Please try again!");
			System.exit(1);
		}
	}

	private void readFile() {
		// init numWords and words[]
		numWords = scanner.nextInt();
		words = new String[numWords];
		scanner.nextLine();

		try {
			// read all words into words[]
			int currWords = 0;
			while(currWords < numWords) {
				words[currWords++] = scanner.nextLine();
			}

			if (scanner.hasNext()) {
				throw new WrongAmountException();
			}
		} catch (WrongAmountException e) {
			System.out.println("Wrong amount of words compared to first line!");
			System.exit(1);
		} catch (NoSuchElementException e) {
			System.out.println("Wrong amount of words compared to first line!");
			System.exit(1);
		}
	}

	public String[] getWords() {
		return words;
	}
}

class WrongAmountException extends Exception {
	
}