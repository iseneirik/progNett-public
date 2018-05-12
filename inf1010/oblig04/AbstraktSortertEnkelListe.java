import java.util.Iterator;

public interface AbstraktSortertEnkelListe<E extends Comparable<E> & Lik> {

	public void settInn(E element);
	public E hent(String nokkel);
	public Iterator<E> hentIterator();

}