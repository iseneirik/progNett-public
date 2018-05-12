class Oppgave6 {
	public static void main(String[] args) {
		NummerertBeholder<Bok> bokhylle = new NummerertBeholder<Bok>(50);
		bokhylle.settInn(4, new Bok("Douglas Adams", "Hitchhikers Guide to the Galaxy"));
		bokhylle.settInn(8, new Bok("J.R.R. Tolkien", "Lord of the Rings"));
		bokhylle.settInn(49, new Bok("Robert Jordan", "Wheel of Time"));

		Bok b = bokhylle.taUt(8);
		System.out.println(b.hentForfatter() + " - " + b.hentTittel());
		b = bokhylle.taUt(4);
		System.out.println(b.hentForfatter() + " - " + b.hentTittel());
		b = bokhylle.taUt(49);
		System.out.println(b.hentForfatter() + " - " + b.hentTittel());
	}
}

class NummerertBeholder<T> {
	private T[] objekter;
	private int antall;

	NummerertBeholder(int antall) {
		this.antall = antall;
		objekter = (T[]) new Object[antall];
	}

	public boolean settInn(int pos, T t) {
		if (pos < 0 || pos >= antall) {
			return false;
		} else {
			objekter[pos] = t;
			return true;
		}
	}

	public T taUt(int pos) {
		if (pos < 0 || pos >= antall) {
			return null;
		} else {
			return objekter[pos];
		}
	}
}