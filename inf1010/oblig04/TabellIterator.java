import java.util.Iterator;

public class TabellIterator<E> implements Iterator<E> {

	private E[] elementer;
	private int currIndex;
	private int nextIndex;

	public TabellIterator(E[] elementer) {
		this.elementer = elementer;
		currIndex = -1;
		nextIndex = findNextIndex(currIndex);
	}

	public boolean hasNext() {
		if (nextIndex < elementer.length) return true;
		return false;
	}

	public E next() {
		E next = null;
		if (hasNext()) {
			next = elementer[nextIndex];
			currIndex = nextIndex;
			nextIndex = findNextIndex(nextIndex);
		}
		return next;
	}

	public void remove() {
		if (currIndex >= 0 && currIndex < elementer.length)	elementer[currIndex] = null;
	}

	private int findNextIndex(int startIndex) {
		int index = startIndex + 1;
		while (index < elementer.length && elementer[index] == null) index++;
		return index;
	}
}