package no.uio.ifi.lt.ranking;

import java.util.logging.Logger;

import no.uio.ifi.lt.indexing.Posting;
import no.uio.ifi.lt.indexing.PostingList;
import no.uio.ifi.lt.search.IQuery;
import no.uio.ifi.lt.storage.IDocument;
import no.uio.ifi.lt.tokenization.IToken;

public class TfIdfRanker implements IRanker {

	
	private Logger logger;
	private boolean debug;
	//private int hitCount;
	private double accumulatedResult;

	public TfIdfRanker(Logger logger) {
		this.logger = logger;
		this.debug = false;
		this.reset();
	}
	
	/**
	 * Implements the {@link IRanker} interface.
	 */
	// Override
	public IRanker clone() {
		return new TfIdfRanker(this.logger);
	}
	
	// Override
	
	public void debug(boolean value) {
		this.debug = value;
	}

	// Override
	public double evaluate(IQuery query, IDocument document) {
		// TODO Auto-generated method stub
		return this.accumulatedResult;
	}

	
	// Override
	public void reset() {
		this.accumulatedResult = 0;
	}

	// Override
	public void update(IToken token, Posting posting, PostingList postingList) {

        // Term frequency (i.e., the number of times the term occurs in the
        // document) is determined by looking at the length of the list of
        // positions.

        int tfCount = posting.getOccurrenceCount();


        // Document frequency (i.e., the number of documents that contain the
        // term) is determined by looking at the length of the term's posting
        // list.

        int dfCount = postingList.size();


        // Normalize the TF count to account for differences in document
        // lengths. Not optional, really, since if we don't do this then rank
        // scores across documents are hard to compare. If we don't do it, we
        // are implicitly assuming that all documents have the same length.
        // TODO: Implement this.

        double tfValue = tfCount;


        // Normalize the DF count by considering the number of documents in
        // the corpus. Somewhat optional, since the size of the corpus is a constant
        // that doesn't vary making the rank scores hard to compare across documents.
       

        double idfValue = Math.log(10000 / dfCount);

        // Add in the TF*IDF contribution from this token.

        this.accumulatedResult += tfValue * idfValue;

        
		
	}

	
	
	
}
