package no.uio.ifi.lt.utils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

import no.uio.ifi.lt.storage.IDocument;
import no.uio.ifi.lt.storage.IDocumentStore;
import no.uio.ifi.lt.tokenization.IToken;
import no.uio.ifi.lt.tokenization.ITokenizer;


/**
 * Simple class for representing a suffix array over
 * the keys in a {@link IDocumentStore} object.
 *
 * @author aleks
 */
public class SuffixArray {


    /**
     * The dictionary whose keys this suffix array is for.
     */
    private final IDocumentStore dictionary;

    /**
     * The array of suffixes for documents in dictionary
     */
    private final Entry[] suffixArray;

    /**
     * Constructor.
     *
     * @param dictionary the dictionary whose keys this suffix array is for
     * @param tokenizer  the tokenizer that will determine where the suffixes start
     */
    public SuffixArray(IDocumentStore dictionary, ITokenizer tokenizer) {
        this.dictionary = dictionary;
        this.suffixArray = buildSuffixArray(this.dictionary, tokenizer);
    }

    /**
     * A simple class to store document id and positions
     * of each suffix in the suffixArray
     *
     * @author iseneirik
     */
    private class Entry implements Comparator<Entry>, Comparable<String> {

        /**
         * Id of the Document in which this suffix is found
         */
        private final int documentId;

        /**
         * Offset of the suffix within the Document
         */
        private final int offset;

        /**
         * Constructor.
         *
         * @param documentId the document in which this suffix is found
         * @param offset the offset of the suffix from the start of the document
         */
        public Entry(int documentId, int offset) {
            this.documentId = documentId;
            this.offset = offset;
        }

        public int getDocumentId() {
            return documentId;
        }

        public int getOffset() {
            return offset;
        }

        @Override
        public String toString() {
            return dictionary.getDocument(this.documentId).getOriginalData().substring(this.offset);
        }

        /**
         * Compare method that compares the suffixes defined by the documentId and offset,
         * Essentially a comparison of substrings of documents
         *
         * @param e1 The first entry to compare
         * @param e2 The second entry to compare
         * @return e1.toString.compareTo(s2.toString)
         */
        @Override
        public int compare(Entry e1, Entry e2) {
            return e1.toString().compareTo(e2.toString());
        }

        @Override
        public int compareTo(String s) {
            return this.toString().compareTo(s);
        }
    }

    /**
     * Builds a suffix array up from the keys in the given dictionary.
     *
     * @param dictionary the dictionary whose keys we want to generate a suffix array for
     * @param tokenizer  the tokenizer that will determine where the suffixes start
     * @return the generated suffix array
     */
    private Entry[] buildSuffixArray(IDocumentStore dictionary, ITokenizer tokenizer) {

        // First, count how many expanded items there are. That's easy:
        int expandedCount = 0;

        for (int i = 0; i < dictionary.size(); ++i) {
            String key = dictionary.getDocument(i).getOriginalData();
            Iterator<IToken> iterator = tokenizer.iterator(key);
            while (iterator.hasNext()) {
            	iterator.next();
                ++expandedCount;
            }
        }

        // Allocate memory.
        Entry[] suffixArray = new Entry[expandedCount];

        // Create Entries for all suffixes based on offset (getStartIndex())
        int currIndex = 0;
        for (int i = 0; i < dictionary.size(); ++i) {
            String key = dictionary.getDocument(i).getOriginalData();
            Iterator<IToken> iter = tokenizer.iterator(key);
            while (iter.hasNext()) {
                IToken currToken = iter.next();
                suffixArray[currIndex++] = new Entry(i, currToken.getStartIndex());
            }
        }

        // Sort the suffixArray with compare(Entry, Entry)
        Arrays.sort(suffixArray, new Entry(0,0));

        return suffixArray;

    }

    /**
     * Returns the number of suffixes.
     *
     * @return the number of suffixes
     */
    public int size() {
        return this.suffixArray.length;
    }

    /**
     * Returns the index of the dictionary entry that the given suffix index is for.
     *
     * @param index a suffix index
     * @return a dictionary entry index
     */
    public int getEntry(int index) {
        return suffixArray[index].getDocumentId();
    }

    /**
     * Returns the offset into the dictionary entry that the given suffix index is for.
     *
     * @param index a suffix index
     * @return an offset into a dictionary entry
     */
    public int getOffset(int index) {
        return suffixArray[index].getOffset();
    }

    /**
     * Looks up the given key by performing a binary search. A binary search
     * allows prefix matching as well as exact matching.
     * <p/>
     * The caller must ensure that the probe key has the expected case.
     *
     * @param key the key we want to look up in the suffix array
     * @return the suffix index of the key, if found, or the insertion point
     */
    public int lookup(String key) {
        return Arrays.binarySearch(suffixArray, key);
    }

}
