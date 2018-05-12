import java.util.Random;

class Logic {
	int generateLoveScore(String name1, String name2) {
		int result = 0;

		Random r = new Random(name1.charAt(0));
		result = r.nextInt();

		r = new Random(name2.charAt(0));
		result += r.nextInt();

		result = result%101;

		return result;
	}
}