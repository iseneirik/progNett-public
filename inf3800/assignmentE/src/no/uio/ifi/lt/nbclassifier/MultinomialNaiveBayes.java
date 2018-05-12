
package no.uio.ifi.lt.nbclassifier;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;

import no.uio.ifi.lt.indexing.IInvertedIndex;
import no.uio.ifi.lt.indexing.ILexicon;
import no.uio.ifi.lt.indexing.InMemoryLexicon;
import no.uio.ifi.lt.indexing.Posting;
import no.uio.ifi.lt.storage.IDocument;
import no.uio.ifi.lt.storage.IDocumentStore;
import no.uio.ifi.lt.tokenization.IToken;
import no.uio.ifi.lt.tokenization.Token;
import no.uio.ifi.lt.utils.Sieve;


/**
 * Representation of a multinomial Naive Bayes classifier for document classification.
 * The classifier is composed of a global lexicon, a prior distribution P(c), and
 * a likelihood distribution P(w|c).
 *
 * @author  gisle, plison
 *
 */
public class MultinomialNaiveBayes implements DocumentClassifier {


	/** Lexicon comprising lexicon entries from all the classes */
	ILexicon globalLexicon;


	/** Prior class distribution P(c) */
	Map<Integer,Double> prior;


	/** Likelihood distribution P(w|c).  The probability P(w_i|c_i) is encoded
	 * as the value in likelihood.get(c_i).get(w_i) */
	Map<Integer, Map<Integer,Double>> likelihood;

	// vocabulary size
	int vocSize;

	/**
	 * Constructs a multinomial Naive Bayes classifier from a message store
	 *
	 * @param store the message store
	 */
	public MultinomialNaiveBayes(MessageStore store) {
		constructGlobalLexicon(store);
		constructPrior(store);
		constructLikelihood(store);
	}



	/**
	 * Constructs a global lexicon comprising the lexicon entries for all the classes,
	 * as defined by their local indexes
	 *
	 * @param store the message store
	 */
	private void constructGlobalLexicon (MessageStore store) {

		globalLexicon = new InMemoryLexicon();

		for (int i = 0 ; i < store.getIndexes().length ; i++) {
			IInvertedIndex localIndex = store.getIndexes()[i];
			Iterator<String> iterator = localIndex.getLexicon().iterator();
			while (iterator.hasNext()) {
				globalLexicon.addValue(iterator.next());
			}
		}

	}


	/**
	 * Constructs the prior distribution for the classifier
	 *
	 * @param store the message store
	 */
	private void constructPrior(MessageStore store) {
		/*
		 * relative frequency of each class:
		 * = number of documents of class c / total number of documents
		 */
		prior = new HashMap<Integer,Double>();

		IInvertedIndex [] newIndex = store.getIndexes();
		int countDocs = 0;
		for (int i = 0; i < newIndex.length; i ++){
			// counts total amount of documents
			countDocs += newIndex[i].getDocumentStore().size();
		}
		for (int i = 0; i < newIndex.length; i++){
			// sets the prior distribution for each class
			double priorDist = (double)newIndex[i].getDocumentStore().size()/(double)countDocs;
			prior.put(i, priorDist);

		}
		//throw new RuntimeException("IMPLEMENT THIS METHOD");
	}



	/**
	 * Constructs the likelihood distribution for the classifier
	 *
	 * @param store the message score
	 */
	private void constructLikelihood(MessageStore store) {

		likelihood = new HashMap<Integer,Map<Integer,Double>>();

		for (int classNumber = 0 ; classNumber < store.getIndexes().length ; classNumber++) {
			likelihood.put(classNumber, new HashMap<Integer,Double>());

			//set the total amount of words in the class as the vocabulary size
			int total = globalLexicon.size();
			// fine local inverted index
			IInvertedIndex local = store.getIndexes()[classNumber];
			// find local lexicon
			ILexicon localLexicon = local.getLexicon();

			// iteration through all the words in globalLexicon
			for (String token: globalLexicon){

				int occurrences = 0;
				// find the index of the word in local lexicon
				int localIndex = localLexicon.lookup(token);
				// if the word is contained in the local lexicon, iterate through the posting list for this token
				if (localIndex != ILexicon.INVALID){
					// the number of documents in the class that contain given term
					int docCount = local.getPostingList(localIndex).size();
					for (int doc = 0; doc < docCount; doc++) {
						// the number of occurrences of the given term in each document
						Posting p = local.getPostingList(localIndex).getPosting(doc);
						// sum of all the occurrences of the token in the class documents
						occurrences += p.getOccurrenceCount();
					}

				}
				// update the total amount of words in the whole class
				total += occurrences;
				// saving the current number of occurrences in the hashMap
				likelihood.get(classNumber).put(globalLexicon.lookup(token), (double)occurrences);

			}
			// iterate through the whole list once again to update the occurrence
			for (String token:globalLexicon){

				double occurrences = likelihood.get(classNumber).get(globalLexicon.lookup(token));
				likelihood.get(classNumber).put(globalLexicon.lookup(token), (occurrences + 1)/total);
			}

			// NB: the lexicon IDs from the local (class-specific) indexes will generally not
			// correspond to the same lexicon IDs as in the global lexicon!
			// constructing local lexicon

			//throw new RuntimeException("IMPLEMENT THIS METHOD");
		}
	}


	/**
	 * Classifies the document into one of the possible classes, given
	 * its content.  The returned value is the class number for the class
	 * which has the highest probability given the document content.
	 *
	 * @param documentContent the document content (already normalized and tokenized)
	 * @return the class with highest probability
	 */
	public int classify (List<IToken> documentContent) {

		Sieve<Integer, Double> posterior = new Sieve<Integer, Double>(1);
			/*
			 * posterior probability P(c|d) = argmax ( log P(c) + sum ( log likelihood P (d|c) )
			 */
		for (int index = 0; index < prior.size(); index ++){

			// set score as the prior probability of the document
			double verdi = Math.log(this.getPriorProbability(index));

			for (int i = 0; i < documentContent.size(); i++){
				// for each token in the document: add the likelihood to the score
				IToken token = documentContent.get(i);
				verdi += Math.log(this.getLikelihoodProbability(token.getValue(), index));
			}
			// set the score
			posterior.sift(index, verdi);
		}

		//throw new RuntimeException("IMPLEMENT THIS METHOD");

		return posterior.iterator().next().data;
	}




	/**
	 * Returns the prior probability of a given class
	 *
	 * @param classNumber the class
	 * @return its probability
	 */
	public double getPriorProbability(int classNumber) {
		return prior.get(classNumber);
	}


	/**
	 * Returns the likelihood probability of a word given its class
	 *
	 * @param word the words
	 * @param classNumber the class to condition on
	 * @return the resulting probability
	 */
	public double getLikelihoodProbability(String word, int classNumber) {
		int lexiconID = globalLexicon.lookup(word);
		if (lexiconID != ILexicon.INVALID) {
			return likelihood.get(classNumber).get(lexiconID);
		}
		else {
			return 0.0;
		}
	}


	/**
	 * Return the number of classes in the classifier
	 *
	 * @return the number of classes
	 */
	public int getNumberOfClasses() {
		return prior.size();
	}

}
