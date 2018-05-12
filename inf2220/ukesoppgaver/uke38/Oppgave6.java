class Oppgave6 {
	public static void main(String[] args) {
		TernaryHeap th = new TernaryHeap();
		th.insert(10, "one");
		th.insert(23, "two");
		th.insert(2, "three");
		th.insert(45, "four");
		th.insert(87, "five");
		th.insert(3, "six");
		th.insert(1, "seven");
		th.insert(1234, "eight");
		th.insert(1055, "nine");
		th.insert(1320, "nine");
		th.insert(20, "nine");
		th.insert(30, "nine");
		th.insert(40, "nine");
		th.insert(50, "nine");
		th.insert(6670, "nine");
		th.insert(87450, "nine");
		th.insert(1000, "nine");
		th.printHeap();
		th.deleteMin();
		th.deleteMin();
		th.deleteMin();
		th.deleteMin();
		th.deleteMin();
		th.deleteMin();
		th.deleteMin();
		th.deleteMin();
		th.deleteMin();
		th.deleteMin();
		th.deleteMin();
		th.deleteMin();
		th.deleteMin();
		th.deleteMin();
		th.deleteMin();
		th.deleteMin();
		th.deleteMin();
	}
}

class TernaryHeap implements HeapInterface<String> {
	
	Node[] heapArr;
	int numElements;

	TernaryHeap() {
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

	public void printHeap() {
		for (int i = 1; i != heapArr.length; i++) {
			if (heapArr[i] == null) return;
			System.out.printf("Index: %2d, Priority: %5d\n", i, heapArr[i].pri);
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

	private void percolateDown(int index) {
		int swap = smallestChild(index);
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
		int firstChild = (index*3)-1;
		if (firstChild > numElements) return 0;
		if (firstChild+1 > numElements) return firstChild;
		if (firstChild+2 > numElements) {
			if (heapArr[firstChild].pri < heapArr[firstChild+1].pri) return firstChild;
			else return firstChild + 1;
		} else {
			if (heapArr[firstChild].pri > heapArr[firstChild+1].pri) firstChild++;
			if (heapArr[firstChild].pri > heapArr[firstChild+1].pri) firstChild++;
			return firstChild;
		}			
	}

	private void percolateUp(int index) {
		int parent = (index + 1) / 3;
		if (index > 1 && heapArr[index].pri < heapArr[parent].pri) {
			Node sub = heapArr[index];
			heapArr[index] = heapArr[parent];
			heapArr[parent] = sub;
			percolateUp(parent);
		}
	}

	private void expandHeap() {
		Node[] newArr = new Node[heapArr.length * 2];
		System.arraycopy(heapArr, 1, newArr, 1, numElements);
		heapArr = newArr;
	}
}