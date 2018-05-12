package no.uio.ifi.lt.storage;

import java.util.Iterator;

import no.uio.ifi.lt.indexing.IInvertedIndex;
import no.uio.ifi.lt.indexing.ILexicon;
import no.uio.ifi.lt.tokenization.IToken;
import no.uio.ifi.lt.utils.ArrayIterator;

public class DocumentVector implements IDocumentVector {

	private IToken[] documentTerms;
	private ILexicon lexicon;
	private IInvertedIndex invertedIndex;

	private int numDocs = 10000;

	private double[] documentVector;

	public DocumentVector(IToken[] documentTerms, ILexicon lexicon, IInvertedIndex invertedIndex) {
		this.documentTerms = documentTerms;
		this.lexicon = lexicon;
		this.invertedIndex = invertedIndex;

		documentVector = new double[lexicon.size()];

		for (IToken term : documentTerms) {
			int lexId = lexicon.lookup(term.getValue());
			if (documentVector[lexId] != 0.0) continue;
			double docFreq = Math.log10(numDocs / invertedIndex.getPostingList(lexId).size());
			int termFreq = 0;
			for (IToken currTerm : documentTerms) {
				if (currTerm.getValue().equals(term.getValue())) termFreq++;
			}

			documentVector[lexId] = docFreq * termFreq;
		}
	}

	// Override
	public double get(int lexiconId) {
		return documentVector[lexiconId];
	}

	// Override
	public double getCosineSimilarity(IDocumentVector docVector) {
		double dotProd = 0.0;
		double mag1 = 0.0;
		double mag2 = 0.0;
		double cosSim;

		for (int i = 0; i < docVector.size(); i++) {
			dotProd += this.get(i) * docVector.get(i);
			mag1 += Math.pow(this.get(i), 2);
			mag2 += Math.pow(docVector.get(i), 2);
		}

		mag1 = Math.sqrt(mag1);
		mag2 = Math.sqrt(mag2);

		if (mag1 != 0.0 | mag2 != 0.0) {
			cosSim = dotProd / (mag1 * mag2);
		} else {
			return 0.0;
		}
		return cosSim;
	}

	// Override
	public int size() {
		return documentVector.length;
	}

}
