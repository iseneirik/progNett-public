package no.uio.ifi.lt.ranking;

import java.util.logging.Logger;

import no.uio.ifi.lt.indexing.Posting;
import no.uio.ifi.lt.indexing.PostingList;
import no.uio.ifi.lt.search.IQuery;
import no.uio.ifi.lt.storage.IDocument;
import no.uio.ifi.lt.tokenization.IToken;

/**
 * Implements an simple relevance computation for shingle-based lookups.
 * 
 * @author aleks
 * @see IQueryEvaluator
 * @see ShingleGenerator
 */
public class ShingleRanker implements IRanker {
	
	/**
	 * For emitting log messages, if any.
	 */
	private Logger logger;
	
	/**
	 * Keeps track of how many "hits" we have in the current document.
	 */
	private int hitCount;

	/**
	 * Emit debug information?
	 */
	private boolean debug;

	/**
	 * The shingle size, i.e., the width of our sliding window
	 * over the text buffer,
	 */
	private int width;
	
	/**
	 * Constructor.
	 */
	public ShingleRanker(int width, Logger logger) {
		this.width = width;
		this.logger = logger;
		this.debug = false;
		this.reset();
	}
	
	/**
	 * Implements the {@link IRanker} interface.
	 */
	// Override
	public IRanker clone() {
		return new ShingleRanker(this.width, this.logger);
	}

	/**
	 * Implements the {@link IRanker} interface.
	 */
	// Override
	public void debug(boolean value) {
		this.debug = value;
	}
	
	/**
	 * Implements the {@link IRanker} interface.
	 */
	// Override
	public double evaluate(IQuery query, IDocument document) {
		
		// Spam?
		if (this.debug && this.logger != null) {
			this.logger.finest(String.format("query = '%s'", query.getOriginalQuery()));
			this.logger.finest(String.format("document.original = '%s'", document.getOriginalData()));
			this.logger.finest(String.format("document.extra = '%s'", document.getExtraData()));
		}

		double numKGramsDoc = document.getOriginalData().length() - this.width + 1;
		double numKGramsQuery = query.getOriginalQuery().length() - this.width + 1;

		return (this.hitCount / (numKGramsDoc + numKGramsQuery - this.width));
	}

	/**
	 * Implements the {@link IRanker} interface.
	 */
	// Override
	public void reset() {
		this.hitCount = 0;
	}

	/**
	 * Implements the {@link IRanker} interface.
	 */
	// Override
	public void update(IToken token, Posting posting, PostingList postingList) {
		this.hitCount += posting.getOccurrenceCount();
	}

}
