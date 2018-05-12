import java.io.*;

class WordWriter {
	// words to write and filename
	String[] words;
	String fileName;

	// printWriter for writing to file
	PrintWriter printWriter;

	WordWriter(String[] words, String fileName) {
		try {
			// init words, filename and printWriter
			this.words = words;
			this.fileName = fileName;
			printWriter = new PrintWriter(fileName);
			writeFile();
		} catch (IOException e) {
			// Shut down program if file not found
			System.out.println("Error while reading into file!");
			System.exit(1);
		} finally {
			// close stream in case of error
			printWriter.close();
		}
	}

	private void writeFile() {
		for (String word : words) {
			printWriter.println(word);
		}

		// close stream to file
		printWriter.close();
	}
}