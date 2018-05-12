class Oppgave7 {
	
}

class BinaryTree {
	
	Node root;

	private class Node {
		private int value;

		private Node leftChild;
		private Node rightChild;

		private Node(int value) {
			this.value = value;
		}

		private void insert(int value) {
			if (value < this.value) {
				if (leftChild == null) {
					leftChild = new Node(value);
				} else {
					leftChild.insert(value);
				}
			} else if (value > this.value) { 
				if (rightChild == null) {
					rightChild = new Node(value);
				} else {
					rightChild.insert(value);
				}
			} else {
				if (leftChild.value == value) {
					leftChild.insert(value);
				} else if (rightChild != null) {
					if (rightChild.value == value && rightChild.leftChild == null) {
						rightChild.leftChild = new Node(value);
					} else if (rightChild.value != value) {
						Node newRightChild = new Node(value);
						newRightChild.rightChild = rightChild;
						rightChild = newRightChild;
					} else {
						rightChild.insert(value);
					}
				} else {
					rightChild = new Node(value);
				}
			} 
		}
		

		// Inserts same values in right subtree
		// private void insert(int value) {
		// 	if (value < this.value) {
		// 		if (leftChild == null) {
		// 			leftChild = new Node(value);
		// 		} else {
		// 			leftChild.insert(value);
		// 		}
		// 	} else {
		// 		if (rightChild == null) {
		// 			rightChild = new Node(value);
		// 		} else {
		// 			rightChild.insert(value);
		// 		}
		// 	}
		// }
	}

	BinaryTree() {
		/* Constructor */
	}

	public void insert(int value) {
		// initialize root if not initialized
		if (root == null) {
			root = new Node(value);
		} else {
			root.insert(value);
		}
	}
}