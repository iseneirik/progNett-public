import java.util.*;

class Oppgave5 {
	public static void main(String[] args) {
		UbegrensetBeholder<Bok> bibliotek = new UbegrensetBeholder<Bok>();
		Bok b = new Bok("Robert Jordan", "Wheel of Time");
		bibliotek.settInn(b);
		b = new Bok("J.R.R. Tolkien", "Lord of the Rings");
		bibliotek.settInn(b);
		b = new Bok("Douglas Adams", "A Hitchhikers Guide to the Galaxy");
		bibliotek.settInn(b);

		b = bibliotek.taUt(0);
		System.out.println(b.hentForfatter() + " - " + b.hentTittel());
	}
}

class UbegrensetBeholder<T> {
	private ArrayList<T> oppbevaring = new ArrayList<T>();

	public void settInn(T ting) {
		oppbevaring.add(ting);
	}

	public T taUt(int plass) {
		return oppbevaring.get(plass);
	}
}