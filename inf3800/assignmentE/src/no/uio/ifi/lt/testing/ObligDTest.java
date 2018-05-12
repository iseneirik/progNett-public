
package no.uio.ifi.lt.testing;

import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import no.uio.ifi.lt.ranking.TfIdfRanker;
import no.uio.ifi.lt.search.IResult;
import no.uio.ifi.lt.search.IResultSet;
import no.uio.ifi.lt.search.ISearchEngine;
import no.uio.ifi.lt.search.SimpleSearchEngine;
import junit.framework.TestCase;

/**
 * Test program for assignment D
 *
 * @author aleks,plison
 *
 */
public class ObligDTest extends TestCase {

	Logger logger;	

	public ObligDTest() {
		createLogger();
	}


	//	 @Before
	public void createLogger() {
		// Create a logger.
		logger = Logger.getLogger(ObligBTest.class.getName());
		Handler[] handlers = logger.getHandlers();
		for (int i = 0; i < handlers.length; ++i) {
			logger.removeHandler(handlers[i]);
		}
		ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setLevel(Level.ALL);

		//  logger.addHandler(consoleHandler);
		logger.setLevel(Level.ALL);		
	}

	/**
	 * search with TF-IDF on the CRAN document collection
	 */
	public void testCranTfIdf() {

		// CRAN document collection
		String filename = "data/cran.xml";

		// Create a simple search engine and do a simple lookup.
		ISearchEngine engine = new SimpleSearchEngine(filename, logger, new TfIdfRanker(logger));			
		String query = "paper flow lateral pressure body boundary article";

		System.out.println("Looking up '" + query + "' with TF-IDF ranker:");
		long before = System.nanoTime();

		IResultSet results = engine.search(query);
		long after = System.nanoTime();
		System.out.println("Lookup took " + ((after - before) / 1000000.0) + " ms.");
		assertEquals(10, results.size());
        
        // uncomment to see results
		/** for (IResult result : results) {
         System.out.println(result.getRelevance());
         System.out.println(result.getDocument().getOriginalData());
         System.out.println(result.getDocument().getExtraData());
         } */
		
        
        Map<String,Boolean> checks = new HashMap<String,Boolean>();
        checks.put("the present article describes an investigation of", false);
        checks.put("the interaction between shock waves", false);
        checks.put("approximate analytical solutions", false);
        
		checkIdfResults(results, checks);
	}

	

	/**
	 * N-of-M search on the sentences from the WeScience project
	 */
	public void testWeScienceTfIdf() {

		// Collection of 1-line documents
		String filename = "data/wescience.txt";

		// Create a simple search engine and do a simple lookup.
		ISearchEngine engine = new SimpleSearchEngine(filename, logger, new TfIdfRanker(logger));			
		String query = "java do the linux";

		System.out.println("Looking up '" + query + "' with TF-IDF ranker:");
		long before = System.nanoTime();

		IResultSet results = engine.search(query);
		long after = System.nanoTime();
		System.out.println("Lookup took " + ((after - before) / 1000000.0) + " ms.");
		assertEquals(10, results.size());
		
        // uncomment to see results
		/** for (IResult result : results) {
			System.out.println(result.getRelevance());
			System.out.println(result.getDocument().getOriginalData());
			System.out.println(result.getDocument().getExtraData());
		} */
		
        Map<String,Boolean> checks = new HashMap<String,Boolean>();
        checks.put("[10490810]", false);
        checks.put("[10441280]", false);
        checks.put("[10491770]", false);
        
        checkIdfResults(results, checks);
	
	}
    
    
    private void checkIdfResults(IResultSet results, Map<String,Boolean> checks) {
        Iterator<IResult> resultIterator = results.iterator();
        while (resultIterator.hasNext()) {
            IResult result = resultIterator.next();
            for (String check: new HashSet<String>(checks.keySet())) {
                if (result.getDocument().getOriginalData().startsWith(check)) {
                    checks.put(check, true);
                }
            }
        }
        
        for (String check: checks.keySet()) {
            assertTrue(checks.get(check));
        }
    }



	/**
	 * document similarity on the CRAN document collection
	 */
	public void testCranSimilarity() {

		// CRAN document collection
		String filename = "data/cran.xml";

		// Create a simple search engine and do a simple lookup.
		ISearchEngine engine = new SimpleSearchEngine(filename, logger);			
		int docID = 225;

		System.out.println("Looking up document similar to " + docID);
		long before = System.nanoTime();

		IResultSet results = engine.findSimilar(docID);
		long after = System.nanoTime();
		System.out.println("Lookup took " + ((after - before) / 1000000.0) + " ms.");
		assertEquals(10, results.size());
		Iterator<IResult> resultIterator = results.iterator();
		
		// checking the first result
		IResult firstResult = resultIterator.next();
		assertTrue(firstResult.getDocument().getOriginalData().startsWith("the flow past a slender delta wing"));

		IResult secondResult = resultIterator.next();
		assertTrue(secondResult.getDocument().getOriginalData().startsWith("an investigation has been conducted"));

		IResult thirdResult = resultIterator.next();
		assertTrue(thirdResult.getDocument().getOriginalData().startsWith("pressure-distribution and force tests"));

		
	}
	
	

	/**
	 * document similarity on the sentences from the WeScience project
	 */
	public void testWeScienceSimilarity() {

		// Collection of 1-line documents
		String filename = "data/wescience.txt";

		// Create a simple search engine and do a simple lookup.
		ISearchEngine engine = new SimpleSearchEngine(filename, logger);			
		int docID = 9981;

		System.out.println("Looking up document similar to " + docID);
		long before = System.nanoTime();

		IResultSet results = engine.findSimilar(docID);
		long after = System.nanoTime();
		System.out.println("Lookup took " + ((after - before) / 1000000.0) + " ms.");
		assertEquals(10, results.size());
		Iterator<IResult> resultIterator = results.iterator();
		
		 
		// checking the first result
		IResult firstResult = resultIterator.next();
		assertTrue(firstResult.getDocument().getOriginalData().startsWith("[10051350]"));

		IResult secondResult = resultIterator.next();
		assertTrue(secondResult.getDocument().getOriginalData().startsWith("[10051370]"));

		IResult thirdResult = resultIterator.next();
		assertTrue(thirdResult.getDocument().getOriginalData().startsWith("[10051420]"));

		// uncomment to see all results
		/**
		for (IResult result : results) {
				System.out.println(result.getRelevance());
				System.out.println(result.getDocument().getOriginalData());
				System.out.println(result.getDocument().getExtraData());
		}*/
	}


}
