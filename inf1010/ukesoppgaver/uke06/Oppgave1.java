import java.util.Iterator;

class Oppgave1 {
	public static void main(String[] args) {
		
	} // end main
} // end Oppgave1

class EnkelLenkeListe<E extends UtNorsk & OutEnglish> implements Container<E> {
	private Node first;

	EnkelLenkeListe(){
		first = null;
	}

	public void push(E obj) {
		Node newObj = new Node(obj);
		newObj.next = first;
		first = newObj;
	}

	public E top() {
		return first.obj;
	}

	public E pop() {
		E obj = first.obj;
		first = first.next;
		return obj;
	}

	private class Node {
		E obj;
		Node next;

		Node(E obj) {
			this.next = next;
		}
	}	

	public Iterator iterator() {
		return new LenkeIterator();
	}

	class LenkeIterator implements Iterator {
		private Node currNode;

		LenkeIterator() {
			currNode = first;
		}

		public E next() {
			Node temp = currNode;
			currNode = currNode.next;
			return temp.obj;
		}

		public boolean hasNext() {
			return currNode != null;
		}

		public void remove() {}
	}
}

class HundDog implements UtNorsk, OutEnglish {
	private String name;
	private int age;

	HundDog(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public void skriv() {
		System.out.printf("Jeg heter %s! Jeg er %d aar gammel!%n", name, age);
	}

	public void write() {
		System.out.printf("My name is %s! I am %d years old!%n", name, age);
	}
}

/*
class DobbelLenkeListe implements Container {
	
}
*/

interface Container<E extends UtNorsk & OutEnglish> extends Iterable{
	public void push(E obj);
	public E top();
	public E pop();
}

interface UtNorsk {
	public void skriv();
} // end UtNorsk

interface OutEnglish {
	public void write();
} // end OutEnglish