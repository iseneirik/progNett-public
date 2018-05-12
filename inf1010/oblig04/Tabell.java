import java.util.Iterator;

public class Tabell<E> implements AbstraktTabell<E>, Iterable<E>{

	public static final int MAX_ANTALL_ELEMENTER = 256;

	private E[] elementer;
	private int antallElementer;

	public Tabell(int lengde) {
		elementer = (E[]) new Object[lengde];
		antallElementer = 0;

	}

	public boolean settInn(E element, int index) {
		System.out.println(index);
		if (index >= elementer.length) {
			if (index <= elementer.length*2) {
				forstorrElementliste();
			} else {
				System.out.println("index: " + index + ", length: " + elementer.length);
				return false;
			}
		}

		if (elementer[index] != null) return false;
		elementer[index] = element;
		antallElementer++;
		return true;
	}

	public boolean settInn(E element) {
		int nyIndex = -1;
		boolean suksess = false;
		for (int i = 0; i < elementer.length; i++) {
			if (elementer[i] == null) {
				nyIndex = i;
				break;
			}
		}

		if (nyIndex < 0 ) {
			nyIndex = elementer.length;
			forstorrElementliste();
		}

		elementer[nyIndex] = element;
		return true;
	}



	public E hent(int index) {
		if (index >= antallElementer) { return null; }
		return elementer[index];
	}

	public Iterator<E> iterator() {
		return hentIterator();
	}

	public Iterator<E> hentIterator() {
		return new TabellIterator<E>(elementer);
	}

	private void forstorrElementliste() {
		E[] nyElementListe = (E[]) new Object[elementer.length*2];
		for (int i = 0; i < elementer.length; i++) {
			nyElementListe [i] = elementer[i];
		}
		elementer = nyElementListe;
	}
}