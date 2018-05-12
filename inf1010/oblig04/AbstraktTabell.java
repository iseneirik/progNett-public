import java.util.Iterator;

public interface AbstraktTabell<E> {

	public boolean settInn(E element, int index);

	public boolean settInn(E element);

	public E hent(int index);

	public Iterator<E> hentIterator();

}