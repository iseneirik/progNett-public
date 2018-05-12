public class Node<E extends Comparable<E> & Lik> {

	E element;
	Node<E> foreldre;
	Node<E> venstre;
	Node<E> hoyre;

	public Node(Node<E> foreldre, E element) {
		this.foreldre = foreldre;
		this.element = element;
	}

	public void settInn(E element) {
		if (element.compareTo(this.element) < 0) {
			if (venstre == null) {
				venstre = new Node<E>(this, element);
			} else {
				venstre.settInn(element);
			}
		} else {
			if (hoyre == null) {
				hoyre = new Node<E>(this, element);
			} else {
				hoyre.settInn(element);
			}
		}
	}

	public E hent(String nokkel) {
		E elem = null;
		if (element.samme(nokkel)) elem = element;
		if (elem == null && venstre != null) elem = venstre.hent(nokkel);
		if (elem == null && hoyre != null) elem = hoyre.hent(nokkel);
		return elem;
	}

	boolean erVenstre() {
		if (foreldre != null && foreldre.venstre == this) return true;
		return false;
	}

	boolean erHoyre() {
		if (foreldre != null && foreldre.hoyre == this) return true;
		return false;
	}

	boolean erTop() {
		if (foreldre == null) return true;
		return false;
	}

}