class BinSearchTree {
	// The root of the binary search tree
	private Node root;

	private int depth;
	private int[] nodesByDepth;
	private int numberOfNodes;

	public BinSearchTree() {
		numberOfNodes = 0;
	} // end Constructor()

	public class Node {
		// Every node has a word
		private String word;

		// Pointer to parent
		private Node parent;

		// The nodes children
		private Node leftChild;
		private Node rightChild;

		// Constructor
		private Node(String word) {
			this.word = word;
		} // end Constructor

		
		public Node findParent(String newWord) {
			/* Searches left child if newWord is lesser than this.word
			   Searches right child if newWord is greater than this.word
			   if newWord is found (newWord == this.word), returns the Node that holds it
			   if newWord is not found, returns parent where it is to be adopted */

			if (newWord.compareTo(this.word) < 0) {
				if (this.leftChild == null) {
					return this;
				} else {
					return leftChild.findParent(newWord);
				}
			} else if (newWord.compareTo(this.word) > 0) {
				if (this.rightChild == null) {
					return this;
				} else {
					return rightChild.findParent(newWord);
				}
			} else {		
				return this;
			}
		} // end findParent()

		private String downLeft() {
			if (leftChild != null) {
				return leftChild.downLeft();
			} else {
				return word;
			}
		} // end downLeft()

		private String downRight() {
			if (rightChild != null) {
				return rightChild.downRight();
			} else {
				return word;
			}
		}

		private void findDepth(int currDepth) {
			currDepth++;

			if (leftChild == null && rightChild == null) {
				if (currDepth > depth) {
					depth = currDepth;
				}
			} else {
				if (leftChild != null) {
					leftChild.findDepth(currDepth);
				}
				if (rightChild != null) {
					rightChild.findDepth(currDepth);
				}
			}
		} // end findDepth()

		private void countNodesByDepth(int currDepth) {
			nodesByDepth[currDepth++]++;
			if (leftChild != null) {
				leftChild.countNodesByDepth(currDepth);
			}
			if (rightChild != null) {
				rightChild.countNodesByDepth(currDepth);
			}
		}

		private boolean isLeaf() {
			return (leftChild == null && rightChild == null);
		} // end isLeaf()

		private boolean hasOneChild() {
			boolean oneChild = (leftChild == null && rightChild != null) ||
			    			   (leftChild != null && rightChild == null);
			return oneChild;				
		}

		private Node findSwap() {
			if (rightChild == null) {
				return this;
			} else {
				return rightChild.findSwap();	
			} 
		} // end findSwap()
	} // end Node
	
	public int getNumNodes() {
		return numberOfNodes;
	} // end getNumNodes()

	public int getDepth() {
		depth = 0;
		root.findDepth(-1);
		return depth;
	} // end getDepth()

	public int[] getNodesByDepth() {
		nodesByDepth = new int[getDepth()+1];
		root.countNodesByDepth(0);
		return nodesByDepth;
	} // end getNodesByDepth()

	public String getFirstWord() {
		return root.downLeft();
	} // end getFirstWord()

	public String getLastWord() {
		return root.downRight();
	} // end getLastWord()


	public boolean containsWord(String newWord) {
		// if newWord exists, wordNode = null
		Node wordNode = root.findParent(newWord);
		return wordNode.word.equalsIgnoreCase(newWord);
	} // end containsWord()

	public void insertWord(String newWord) {
		if (root == null) {
			root = new Node(newWord);
			numberOfNodes++;
			return;
		}

		Node parentNode = root.findParent(newWord);

		if (parentNode.word.equalsIgnoreCase(newWord)) {
			System.out.println("Word already exists!");
		} else {
			if (newWord.compareTo(parentNode.word) < 0) {
				parentNode.leftChild = new Node(newWord);
				parentNode.leftChild.parent = parentNode;
			} else {
				parentNode.rightChild = new Node(newWord);
				parentNode.rightChild.parent = parentNode;

			}
			numberOfNodes++;
		}
	} // end insertWord()

	public void deleteWord(String delWord) {
		Node delNode = root.findParent(delWord);

		if (!delNode.word.equalsIgnoreCase(delWord)) {
			System.out.printf("\"%s\" was not found!\n", delWord);
		} else {
			deleteNode(delNode);
		}
		numberOfNodes--;
	} // end deleteWord()

	private void deleteNode(Node delNode) {
		if (delNode.isLeaf()) {
			// If the node is a leaf, delete it
			Node parent = delNode.parent;
			if (parent == null) {
				// if parent is null, make root null
				root = null;
			} else if (parent.leftChild == delNode) {
				parent.leftChild = null;
			} else {
				parent.rightChild = null;
			}
		} else if (delNode.hasOneChild()) {
			// If the node only has one child, skip it
			// lonelyChild becomes the only child delNode has
			Node lonelyChild = (delNode.leftChild == null ? delNode.rightChild : delNode.leftChild);
			Node parent = delNode.parent;

			// If delNode is the root, make lonelyChild the root
			if (parent == null) {
				root = lonelyChild;
			} else if (parent.leftChild == delNode) {
				// if delNode is parents leftchild, reassign leftChild
				parent.leftChild = lonelyChild;
			} else {
				// if delNode is parents rightChild, reassign rightChild
				parent.rightChild = lonelyChild;
			}
		} else {
			// if the node has two children, find a node to swap it with
			Node swapNode = delNode.leftChild.findSwap();
			delNode.word = swapNode.word;
			// delete the node it swapped with
			deleteNode(swapNode);
		}
	} // end deleteNode()
} // end BinSearchTree