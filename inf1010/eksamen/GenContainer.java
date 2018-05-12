import java.util.*;

class GenContainer<T> implements Iterable<T> {
	// First Node in the list
	private Node first;


	// Nodes store items in a list
	private class Node {
		T item;
		Node next;

		Node(T item) {
			this.item = item;
			next = null;
		}
	}

	// Last in First out approach
	public void putItemLIFO(T item) {
		Node newItem = new Node(item);
		if (first == null) { 
			first = newItem; 
		} else {
			newItem.next = first;
			first = newItem;
		}
	}

	// // First in First out approach
	// public void putItemFIFO(T item) {
	// 	Node newItem = new Node(item);
	// 	if (first == null) {
	// 		first = newItem;
	// 	} else {
	// 		Node lastNode = first;
	// 		while(lastNode.next != null) {
	// 			lastNode = lastNode.next;
	// 		}
	// 		lastNode.next = newItem;
	// 	}
	// }

	// Removes first item
	public void removeFirst() {
		Node subFirst = first;
		first = first.next;
		subFirst.next = null;
	}

	// Removes last item
	public void removeLast() {
		Node lastNode = first;
		while(lastNode.next.next != null) {
			lastNode = lastNode.next;
		}
		lastNode.next = null;

	}

	// Gets the iterator
	public Iterator<T> iterator() {
		return new GenContainerIterator();
	}

	// Iterator for the linkedList
	private class GenContainerIterator implements Iterator<T> {
		Node currNode = first;

		public T next() {
			Node subNode = currNode;
			currNode = currNode.next;
			return subNode.item;
		}

		public boolean hasNext() {
			if (currNode != null) {
				return true;
			} else {
				return false;
			}
		}

		public void remove() {

		}
	}

}