import java.util.Arrays;

class Haystack {
	private final char wildcard = '_';
	private final String text;
	private int[] badCharShift;

	Haystack (String text) {
		this.text = text;
	}

	public void findNeedle(String needle) {
		badCharShift = new int[256];
		setBadCharShift(needle);
		int needlelen = needle.length();

		int index = 0;
		while(index <= (text.length() - needlelen)) {
			String comp = new String(text.substring(index, index + needlelen));
			if (matchesPattern(comp, needle))
				System.out.printf("Index %d: %s\n", index, comp);
			index += badCharShift[comp.charAt(needlelen - 1)];
		}
	}

	private boolean matchesPattern(String comp, String needle) {
		int i = needle.length() - 1;
		while (i != -1) {
			if (needle.charAt(i) == wildcard || needle.charAt(i) == comp.charAt(i)) i--;
			else break;		
		}
		return (i == -1);
	}

	private void setBadCharShift(String needle) {	
		Arrays.fill(badCharShift, needle.length());	
		int shift = needle.length() - 1;
		if (shift == 0) return;
		for (char c : needle.toCharArray()) {
			if (c == '_')
				Arrays.fill(badCharShift, shift);
			else badCharShift[c] = shift;
			if (--shift == 0) break;
		}
	}
}