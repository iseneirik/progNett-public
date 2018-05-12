package no.uio.ifi.lt.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import no.uio.ifi.lt.indexing.IInvertedIndex;
import no.uio.ifi.lt.indexing.ILexicon;
import no.uio.ifi.lt.indexing.Posting;
import no.uio.ifi.lt.indexing.PostingList;
import no.uio.ifi.lt.preprocessing.INormalizer;
import no.uio.ifi.lt.ranking.IRanker;
import no.uio.ifi.lt.storage.IDocument;
import no.uio.ifi.lt.storage.IDocumentStore;
import no.uio.ifi.lt.tokenization.IToken;
import no.uio.ifi.lt.tokenization.ITokenizer;
import no.uio.ifi.lt.utils.HeapItem;
import no.uio.ifi.lt.utils.Sieve;

/**
 * Implements the query evaluation logic in a search engine.
 * 
 * @author aleks
 */
public class QueryEvaluator implements IQueryEvaluator {

	/**
	 * Defines the evaluation parameters.
	 */
	private QueryEvaluatorSettings settings;	
	
	/**
	 * Where we emit messages, if at all.
	 */
	private Logger logger;
	
    /**
     * Constructor.
     * 
     * @param settings defines the evaluation parameters
     * @param logger defines where to emit log messages, if at all
     */
    public QueryEvaluator(QueryEvaluatorSettings settings, Logger logger) {
    	this.settings = settings;
    	this.logger = logger;
    }
	
	/**
	 * Implements the {@link IQueryEvaluator} interface.
	 */
	// Override
	public IResultSet evaluate(IQuery query, IInvertedIndex invertedIndex, IRanker ranker) {
		
		// Paranoia.
        if (query.getNormalizedLength() == 0) {
        	return new ResultSet(query, 0);
        }

        // Spam the logs?
        boolean debug = this.settings.debug && (this.logger != null) && this.logger.isLoggable(Level.FINEST);

        // Should the ranker spam the logs as well?
        ranker.debug(debug);
        
        // Synchronize query processing with document processing.
        INormalizer normalizer = invertedIndex.getNormalizer();
        ITokenizer tokenizer = invertedIndex.getTokenizer();
        IDocumentStore documentStore = invertedIndex.getDocumentStore();

        // Process a normalized version, not the raw value.
        String normalizedQuery = normalizer.normalize(query.getOriginalQuery());

        // Split the query string up into terms.
        IToken[] queryTerms = tokenizer.toArray(normalizedQuery);

		// Get PostingLists for each term in the query
		ArrayList<PostingList> postingLists = getQueryPostingLists(queryTerms, invertedIndex);

		// Rank and sift the documents
		Sieve<IDocument, Double> topTen = new Sieve<IDocument, Double>(this.settings.candidates);
		int[] docIndexes = new int[postingLists.size()];
		for (int docID = 0; docID < documentStore.size(); ++docID) {
			for (int plID = 0; plID < docIndexes.length; ++plID) {
				if (docIndexes[plID] >= postingLists.get(plID).size())
					continue; // No more documents in PostingList
				if (postingLists.get(plID).getPosting(docIndexes[plID]).getDocumentId() == docID) {
					// Matching document ID's, update documents count
					ranker.update(queryTerms[plID], postingLists.get(plID).getPosting(docIndexes[plID]), postingLists.get(plID));
					docIndexes[plID]++; // Move past document after term is found
				}
			}
			topTen.sift(documentStore.getDocument(docID), ranker.evaluate(query, documentStore.getDocument(docID)));
			ranker.reset();
		}

		// Move the sifted items over to the ResultSet
		ResultSet results = new ResultSet(query, this.settings.candidates);
		for (HeapItem<IDocument, Double> currItem : topTen) {
			results.appendResult(new Result(currItem.data, currItem.rank));
		}

		// Sort the ResultSet and return it
		results.sortByRelevance();
		return results;
	}

	/**
	 * Get PostingLists for each term in the query
	 *
	 * @param queryTerms The terms for which we get PostingLists
	 * @param invertedIndex The invertedIndex where the lexicon and PostingLists are
     * @return An ArrayList of PostingLists for the query terms
     */
	public ArrayList<PostingList> getQueryPostingLists(IToken[] queryTerms, IInvertedIndex invertedIndex) {
		ILexicon lexicon = invertedIndex.getLexicon();
		ArrayList<PostingList> postingLists = new ArrayList<>();
		for (IToken token : queryTerms) {
			int lexiconIndex = lexicon.lookup(token.getValue());
			if (lexiconIndex == ILexicon.INVALID) {
				postingLists.add(new PostingList()); // Not a known term
			} else {
				postingLists.add(invertedIndex.getPostingList(lexiconIndex));
			}
		}
		return postingLists;
	}

	
}
