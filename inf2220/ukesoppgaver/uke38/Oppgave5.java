import java.util.ArrayList;

class Oppgave5 {
	public static void main(String[] args) {
		StringBinHeap bh = new StringBinHeap();
		bh.insert(10, "one");
		bh.insert(23, "two");
		bh.insert(2, "three");
		bh.insert(45, "four");
		bh.insert(87, "five");
		bh.insert(3, "six");
		bh.insert(1, "seven");
		bh.insert(1234, "eight");
		bh.insert(1055, "nine");
		bh.insert(1320, "nine");
		bh.insert(20, "nine");
		bh.insert(30, "nine");
		bh.insert(40, "nine");
		bh.insert(50, "nine");
		bh.insert(6670, "nine");
		bh.insert(87450, "nine");
		bh.insert(1000, "nine");
		bh.printHeap();
		bh.deleteMin();
		bh.deleteMin();
		bh.deleteMin();
		bh.deleteMin();
		bh.deleteMin();
		bh.deleteMin();
		bh.deleteMin();
		bh.deleteMin();
		bh.deleteMin();
		bh.deleteMin();
		bh.deleteMin();
		bh.deleteMin();
		bh.deleteMin();
		bh.deleteMin();
		bh.deleteMin();
		bh.deleteMin();
		bh.deleteMin();
	} // end main()
} // end Oppgave5

interface HeapInterface<T> {

	public void insert(int pri, T o);
	public T deleteMin();
} // end HeapInterface<T>

class StringBinHeap implements HeapInterface<String> {
	
	Node[] heapArr;
	int numElements;

	StringBinHeap() {
		heapArr = new Node[16];
		numElements = 0;
	}

	private class Node {
		int pri;
		String str;

		Node(int pri, String str) {
			this.pri = pri;
			this.str = str;
		}
	}

	public void insert(int pri, String o) {
		if (numElements == heapArr.length-1) expandHeap();
		heapArr[++numElements] = new Node(pri, o);
		percolateUp(numElements);
	}

	public String deleteMin() {
		int pri = heapArr[1].pri;
		String deleted = heapArr[1].str;
		heapArr[1] = heapArr[numElements];
		heapArr[numElements--] = null;
		percolateDown(1);
		System.out.println("" + pri);
		return deleted;
	}

	public void printHeap() {
		for (int i = 1; i != heapArr.length; i++) {
			if (heapArr[i] == null) return;
			System.out.printf("Index: %2d, Priority: %5d\n", i, heapArr[i].pri);
		}
	}

	private void percolateUp(int index) {
		if (index > 1 && heapArr[index].pri < heapArr[index/2].pri) {
			Node sub = heapArr[index];
			heapArr[index] = heapArr[index/2];
			heapArr[index/2] = sub;
			percolateUp(index/2);
		}
	}

	private void percolateDown(int index) {
		int swap = smallestChild(index*2);
		if (swap != 0) {
			if (heapArr[index].pri > heapArr[swap].pri) {
				Node sub = heapArr[index];
				heapArr[index] = heapArr[swap];
				heapArr[swap] = sub;
				percolateDown(swap);
			}
		}

	}

	private int smallestChild(int index) {
		if (index > numElements) return 0;
		if (index+1 > numElements) return index;
		if (heapArr[index].pri < heapArr[index+1].pri) return index;
		return index + 1;
	}

	private void expandHeap() {
		Node[] newArr = new Node[heapArr.length * 2];
		System.arraycopy(heapArr, 1, newArr, 1, numElements);
		heapArr = newArr;
	}
}

// System.arrayCopy(fraArr, fraPos, tilArr, tilPos, lengde)