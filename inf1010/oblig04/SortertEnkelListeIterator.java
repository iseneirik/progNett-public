import java.util.Iterator;

public class SortertEnkelListeIterator<E extends Comparable<E> & Lik> implements Iterator<E> {

	private Node<E> topNode;
	private Node<E> forste;
	private Node<E> siste;
	private Node<E> neste;
	private Node<E> current;

	private boolean deleteLeft = false;

	public SortertEnkelListeIterator(Node<E> topNode) {
		this.topNode = topNode;
		finnEnder();
		neste = forste;
		current = null;
	}

	public boolean hasNext() {
		if (neste != null) return true;
		return false;
	}

	public E next() {
		current = neste;
		finnNeste();
		return current.element;
	}

	public void remove() {
		if (current != null ) {
			remove(current);
			current = null;
			if (deleteLeft) neste = neste.hoyre;
			deleteLeft = !deleteLeft;
		}
	}

	private void remove(Node<E> node) {
		if (node.venstre == null && node.hoyre == null) {
			removeBarrenNode(node);
		} else if (node.venstre == null || node.hoyre == null) {
			removeNodeWithOneChild(node);
		} else {
			removeNodeWithTwoChildren(node);
		}
	}

	private void removeNodeWithTwoChildren(Node<E> node) {
		Node<E> temp = null;
		if (deleteLeft) {
			temp = node.venstre;
			while (temp.hoyre != null) temp = temp.hoyre;
		} else {
			temp = node.hoyre;
			while (temp.venstre != null) temp = temp.venstre;
		}
		node.element = temp.element;
		remove(temp);
	}

	private void removeBarrenNode(Node<E> node) {
		if (node == topNode) topNode = null;
		else if(node.erVenstre()) node.foreldre.venstre = null;
		else node.foreldre.hoyre = null;
	}


	private void removeNodeWithOneChild(Node<E> node) {
		Node<E> nyForeldre = node.foreldre;
		Node<E> nyBarn = null;
		if (node.venstre == null && node.hoyre != null) {
			nyBarn = node.hoyre;
			if (node.erVenstre()) node.foreldre.venstre = nyBarn;
			else if (node.erHoyre()) node.foreldre.hoyre = nyBarn;
			nyBarn.foreldre = nyForeldre;
		} else if (node.venstre != null && node.hoyre == null) {
			nyBarn = node.venstre;
			if (node.erVenstre()) node.foreldre.venstre = nyBarn;
			else if (node.erHoyre()) node.foreldre.hoyre = nyBarn;
			nyBarn.foreldre = nyForeldre;
		}
	}

	private void finnNeste() {
		if (neste == null) neste = forste;
		else if (neste == siste) neste = null;
		else if (neste.hoyre != null) neste = neste.hoyre;
		else if (neste.foreldre == null) neste = null;
		else if (neste.erVenstre()) neste = neste.foreldre;
		else {
			do {
				neste = neste.foreldre;
			} while (neste.foreldre != null && neste.erHoyre());
			neste = neste.foreldre;
		}
	}

	private void finnEnder() {
		Node<E> temp = topNode;
		while (temp.venstre != null) temp = temp.venstre;
		forste = temp;
		temp = topNode;
		while(temp.hoyre != null) temp = temp.hoyre;
		siste = temp;
	}





}