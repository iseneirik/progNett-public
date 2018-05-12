
package no.uio.ifi.lt.testing;

import java.util.Iterator;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

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
		ISearchEngine engine = new SimpleSearchEngine(filename, logger);			
		String query = "paper flow lateral pressure body boundary article";

		System.out.println("Looking up '" + query + "' with TF-IDF ranker:");
		long before = System.nanoTime();

		IResultSet results = engine.search(query);
		long after = System.nanoTime();
		System.out.println("Lookup took " + ((after - before) / 1000000.0) + " ms.");
		assertEquals(10, results.size());
		Iterator<IResult> resultIterator = results.iterator();

		for (IResult result : results) {
			System.out.println(result.getRelevance());
			System.out.println(result.getDocument().getOriginalData());
			System.out.println(result.getDocument().getExtraData());
		}

		// checking the first result
		IResult firstResult = resultIterator.next();
		assertTrue(firstResult.getDocument().getOriginalData().startsWith("the present article describes an investigation of"));

		IResult secondResult = resultIterator.next();
		assertTrue(secondResult.getDocument().getOriginalData().startsWith("the interaction between shock waves"));

		IResult thirdResult = resultIterator.next();
		assertTrue(thirdResult.getDocument().getOriginalData().startsWith("approximate analytical solutions"));


		// uncomment to see all results
		/**
		  for (IResult result : results) {
			System.out.println(result.getRelevance());
			System.out.println(result.getDocument().getOriginalData());
			System.out.println(result.getDocument().getExtraData());
		} */
		 
	}
	
	

	/**
	 * N-of-M search on the sentences from the WeScience project
	 */
	public void testWeScienceTfIdf() {

		// Collection of 1-line documents
		String filename = "data/wescience.txt";

		// Create a simple search engine and do a simple lookup.
		ISearchEngine engine = new SimpleSearchEngine(filename, logger);			
		String query = "java do the linux";

		System.out.println("Looking up '" + query + "' with TF-IDF ranker:");
		long before = System.nanoTime();

		IResultSet results = engine.search(query);
		long after = System.nanoTime();
		System.out.println("Lookup took " + ((after - before) / 1000000.0) + " ms.");
		assertEquals(10, results.size());
		Iterator<IResult> resultIterator = results.iterator();

		// checking the first result
		IResult firstResult = resultIterator.next();
		assertTrue(firstResult.getDocument().getOriginalData().startsWith("[10490810]"));

		IResult secondResult = resultIterator.next();
		assertTrue(secondResult.getDocument().getOriginalData().startsWith("[10491770]"));

		IResult thirdResult = resultIterator.next();
		assertTrue(thirdResult.getDocument().getOriginalData().startsWith("[10441280]"));

		// uncomment to see all results
		/**
		for (IResult result : results) {
			System.out.println(result.getRelevance());
			System.out.println(result.getDocument().getOriginalData());
			System.out.println(result.getDocument().getExtraData());
		} */
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


		// uncomment to see all results
		 /**
		for (IResult result : results) {
			System.out.println(result.getRelevance());
			System.out.println(result.getDocument().getOriginalData());
			System.out.println(result.getDocument().getExtraData());
		}*/
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
		assertTrue(firstResult.getDocument().getOriginalData().startsWith("[10051350]") || firstResult.getDocument().getOriginalData().startsWith("[10051340]"));

		IResult secondResult = resultIterator.next();
		assertTrue(secondResult.getDocument().getOriginalData().startsWith("[10051370]") || secondResult.getDocument().getOriginalData().startsWith("[10051650]"));

		IResult thirdResult = resultIterator.next();
		assertTrue(thirdResult.getDocument().getOriginalData().startsWith("[10051420]") || thirdResult.getDocument().getOriginalData().startsWith("[10051390]"));

		// uncomment to see all results
		/**
		for (IResult result : results) {
				System.out.println(result.getRelevance());
				System.out.println(result.getDocument().getOriginalData());
				System.out.println(result.getDocument().getExtraData());
		}*/
	}


}
