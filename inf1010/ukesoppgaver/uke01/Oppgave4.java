import java.util.*;

class Oppgave4 {
	public static void main(String[] args) {
		UbegrensetBeholder<Bok> bibliotek = new UbegrensetBeholder<Bok>();
		Bok b = new Bok("Robert Jordan", "Wheel of Time");
		bibliotek.settInn(b.hentTittel(), b);
		b = new Bok("J.R.R. Tolkien", "Lord of the Rings");
		bibliotek.settInn(b.hentTittel(), b);
		b = new Bok("Douglas Adams", "A Hitchhikers Guide to the Galaxy");
		bibliotek.settInn(b.hentTittel(), b);

		System.out.println(bibliotek.taUt("Wheel of Time").hentForfatter() + " - Wheel of Time");
	}
}

class UbegrensetBeholder<T> {
	private HashMap<String,T> objekter = new HashMap<String,T>();

	public void settInn(String nokkel, T ting) {
		objekter.put(nokkel, ting);
	}

	public T taUt(String nokkel) {
		T ting = objekter.get(nokkel);
		objekter.remove(nokkel);
		return ting; 
	}
}