import java.util.*;

class Oblig1 {
	public static void main(String[] args) {
		// Create a dictionary from the provided .txt file
		Dictionary dictionary;
		if (args.length == 1) {
			dictionary = new Dictionary(args[0]);
		} else {
			System.out.println("Wrong format, use: java Oblig1 <file.txt>");
			return;
		}

		// Delete and insert the word "familie"
		dictionary.deleteWord("familie");
		dictionary.insertWord("familie");
		
		// Retrieve the first input and enter program loop
		System.out.println("Welcome to the amazing dictionary searcher!");
		System.out.println("\n------------------------------\n");
		System.out.print("Type in a word ('q' to quit): ");

		Scanner userInput = new Scanner(System.in);
		String searchWord = userInput.next();

		while (!searchWord.equals("q")) {
			// Search dictionary for word or generate similar words
			dictionary.lookUp(searchWord, false);

			// Prompt user for a new word
			System.out.print("\nType in a word ('q' to quit): ");
			searchWord = userInput.next();

			// "flush" the rest of the line (if any)
			userInput.nextLine();
		}
	} // end main()
} // end Oblig1