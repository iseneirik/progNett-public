class Arne {
	static int count = 0;

	public static void printAnagrams(char[] ca, String s) {
		if (ca.length == 0) {
			System.out.println(s);
			count++;
		} else {
			for (char c : ca) {
				printAnagrams(cloneRemove(ca, c), s + c);
			}
		}
	}

	public static char[] cloneRemove(char[] ca, char remove) {
		char[] clone = new char[ca.length - 1];
		int i = 0;
		for (char c : ca) {
			if (c != remove) {
				clone[i++] = c;
			}
		}
		return clone;
	}

	public static void main(String[] args) {
		char[] arne = {'A', 'R', 'N', 'E'};
		printAnagrams(arne, "");
		System.out.println(count);
	}
}