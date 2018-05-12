import java.nio.file.*;

class Oblig3 {
	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.out.println("Correct usage: Oblig3 <needle> <haystack>");
			System.exit(1);
		}

		String haystack = new String(Files.readAllBytes(Paths.get(args[1])));
		String needle = new String(Files.readAllBytes(Paths.get(args[0])));

		System.out.printf("Needle: %s\nHaystack: %s\n\n", needle, haystack);
		Haystack hs = new Haystack(haystack);
		hs.findNeedle(needle);
	}
}