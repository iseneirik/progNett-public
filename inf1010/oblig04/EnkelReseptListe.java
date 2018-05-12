import java.util.Iterator;

class EnkelReseptListe implements Iterable<Resept> {
	// pekere til forste og siste element
	Node first;
	Node last;

	EnkelReseptListe() {
		first = null;
		last = null;
	} // end Constructor

	protected class Node {
		protected Node neste;
		protected Resept resept;

		Node(Resept resept) {
			this.resept = resept;
		} // end Constructor
	} // end Node

	public void settInn(Resept p) {
		Node nyResept = new Node(p);

		if (first == null) {
			first = nyResept;
			last = nyResept;
			return;
		}

		// Setter noden forrerst i listen
		nyResept.neste = first;
		first = nyResept;
	} // end settInn

	public Resept finnResept(int nr) throws ReseptIkkeFunnet {
		for (Resept r : this) {
			if (r.getReseptId() == nr) {
				return r;
			}
		}

		throw (new ReseptIkkeFunnet());
	} // end finnResept

	public Iterator<Resept> iterator() {
		return new ReseptIterator(this);
	} // end iterator

	class ReseptIterator implements Iterator<Resept> {
		Node currNode;

		ReseptIterator(EnkelReseptListe liste) {
			currNode = liste.first;
		} // end Constructor

		public boolean hasNext() {
			return (currNode != null);
		} // end hasNext

		public Resept next() {
			if (hasNext()) {
				Node temp = currNode;
				currNode = currNode.neste;
				return temp.resept;
			}
			return null;
		} // end next

		public void remove() {}
	} // end ReseptIterator

} // end EnkelReseptListe

class ReseptIkkeFunnet extends Exception {
	// Exception om Resepten ikke finnes
} // end ReseptIkkeFunnet

class YngsteForstReseptListe extends EnkelReseptListe {
	// Denne klassen fungerer som EnkelReseptListe
} // end YngsteForstReseptListe

class EldsteForstReseptListe extends EnkelReseptListe {
	// Override av funksjonen settInn
	public void settInn(Resept p) {
		Node nyResept = new Node(p);

		// Setter forste resept til first og last
		if (first == null) {
			first = nyResept;
			last = nyResept;
			return;
		}

		// Setter noden inn omvendt fra originalen
		last.neste = nyResept;
		last = nyResept;
	} // end settInn
} // end EldsteForstReseptListe