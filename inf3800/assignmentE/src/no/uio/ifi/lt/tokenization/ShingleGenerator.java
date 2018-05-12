package no.uio.ifi.lt.tokenization;

import java.util.Iterator;

import no.uio.ifi.lt.utils.ArrayIterator;

/**
 * A simple {@link ITokenizer} implementation that generates overlapping
 * shingles. Useful for approximate matching purposes.
 * 
 * @author aleks
 */
public class ShingleGenerator implements ITokenizer {
	
	/**
	 * The shingle size, i.e., the width of our sliding window
	 * over the text buffer,
	 */
	private int width;

	/**
	 * Constructor.
	 * 
	 * @param width the shingle size
	 */
	public ShingleGenerator(int width) {
		this.width = width;
	}

	/**
	 * Implements the {@link ITokenizer} interface.
	 */
	// Override
	public Iterator<IToken> iterator(String text) {
		return new ArrayIterator<IToken>(this.toArray(text));
	}
	
	/**
	 * Implements the {@link ITokenizer} interface.
	 */
	// Override
	public IToken[] toArray(String text) {

		int numShingles = text.length() - this.width + 1;
		IToken[] shingles = new IToken[numShingles];

		int pos = 0;
		for (int i = 0; i < numShingles; i++) {
			shingles[i] = new Token(text.substring(i, i + this.width), i, pos);
			if (text.charAt(0) == ' ') pos++;
		}

		return shingles;
	}

}
