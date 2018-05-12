import java.util.Iterator;

public class SortertEnkelListe<E extends Comparable<E> & Lik> implements AbstraktSortertEnkelListe<E>, Iterable<E> {

	private Node<E> topNode;

	public SortertEnkelListe() {}

	public SortertEnkelListe(E element) {
		topNode = new Node<E>(null, element);
	}

	public void settInn(E element) {
		if (topNode == null) {
			topNode = new Node<E>(null, element);
		} else {
			topNode.settInn(element);
		}
	}

	public E hent(String nokkel) {
		return topNode.hent(nokkel);
	}

	public Iterator<E> hentIterator() {
		return new SortertEnkelListeIterator<E>(topNode);
	}

	public Iterator<E> iterator() {
		return hentIterator();
	}
}