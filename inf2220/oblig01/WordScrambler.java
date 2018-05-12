import java.util.*;

class WordScrambler {
	final private String originalWord;
	final private char[] alphabet = "abcdefghijklmnopqrstuvwxyzæøå".toCharArray();

	private String[] similarWords;
	private int insertIndex = 0;

	public WordScrambler(String word) {
		originalWord = word;
		similarWords = new String[calcNumWords()];

		similarOne();
		similarTwo();
		similarThree();
		similarFour();
	} // end Constructor()

	// returns the number of words generated based on originalWord length
	private int calcNumWords() {
		int numChars = originalWord.length();
		int alphabetSize = alphabet.length;

		int combos = 0;
		// similarOne() combos
		combos += (numChars - 1);
		// similarTwo() combos
		combos += numChars * alphabetSize;
		// similarThree() combos
		combos += (numChars + 1) * alphabetSize;
		// similarFour() combos
		combos += numChars;

		return combos;
	} // end calcNumWords

	// Generates n-1 similar words
	private void similarOne() {
		for (int i = 0; i < originalWord.length() - 1; i++) {
			// swaps letters index (i) and (i+1)
			similarWords[insertIndex++] = swap(i, i+1, originalWord.toCharArray());
		} 
	} // end similarOne()

	private String swap(int a, int b, char[] word) {
		char tmp = word[a];
		word[a] = word[b];
		word[b] = tmp;
		return new String(word);
	} // end swap()

	// Generates n * alphabet.length similar words
	private void similarTwo() {
		for (int i = 0; i < originalWord.length(); i++) {
			for (int j = 0; j < alphabet.length; j++) {
				// replaces newWord[i] with another letter (j) of the alphabet
				char[] newWord = originalWord.toCharArray();
				newWord[i] = alphabet[j];

				// adds the new newWord to similarWords
				similarWords[insertIndex++] = new String(newWord);
			}
		}
	} // end similarTwo()

	// Generates (n+1) * alphabet.length similar words
	private void similarThree() {
		// newWord has one more character than orgWord
		char[] newWord = new char[originalWord.length()+1];
		char[] orgWord = originalWord.toCharArray();

		for (int newLetter = 0; newLetter < alphabet.length; newLetter++) {
			for (int i = 0; i < newWord.length; i++) {
				for (int j = 0, k = 0; j < newWord.length; j++) {
					if (j == i) {
						// fills the open space with a letter from alphabet
						newWord[j] = alphabet[newLetter];
					} else {
						newWord[j] = orgWord[k++];
					}
				}
				// converts newWord to a String and adds to similarWords
				similarWords[insertIndex++] = new String(newWord);
			}
		} 
	} // end similarThree()

	// Generates n similar words
	private void similarFour() {
		// newWord has one less character than orgWord
		char[] newWord = new char[originalWord.length()-1];
		char[] orgWord = originalWord.toCharArray();

		for (int i = 0; i < originalWord.length(); i++) {
			for (int j = 0, k = 0; j < newWord.length; j++, k++) {
				// skips the letter in index i
				if (k == i) k++;
				newWord[j] = orgWord[k];
			}
			// converts newWord to a String and adds to similarWords
			similarWords[insertIndex++] = new String(newWord);
		}
	} // end similarFour()

	public String[] getSimilarWords() {
		return similarWords;
	} // end getSimilarWords()
} // end WordScrambler