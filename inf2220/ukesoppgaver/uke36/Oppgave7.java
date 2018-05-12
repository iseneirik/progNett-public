import java.util.Arrays;

class Oppgave7 {
	public static void main(String[] args) {
		BTree testTree = new BTree();
	}
}

class BTree {
	// ORDER: Pointers per node
	private final int ORDER = 5;
	// DATA_SIZE: Data per node
	private final int DATA_SIZE = 5;

	BNode root;

	class BNode{
		int[] values;
		BNode[] children;

		int numValues;
		int numPointers;

		BNode() {
			values = new int[DATA_SIZE + 1];
			numValues = 0;
		} // end Constructor()

		BNode(int[] insertVals, int fromIndex) {
			values = new int[DATA_SIZE + 1];
			numValues = 0;

			for (int i = 0; i <  ; ) {
				
			}
		}

		public void insert(int value) {
			values[numValues++] = value;
			Arrays.sort(values, 0, numValues);

			if (numValues == DATA_SIZE) {
				BNode newLeft = new BNode(values[], 0);
				BNode newRight = new BNode(values[], DATA_SIZE / 2);
			}
		}

		boolean isLeaf() {
			return (numPointers == 0);
		} // end isLeaf()
	} // end BNode

	BTree() {
		root = null;
	} // end Constructor()

	public void insert(int value) {
		if (root == null) {
			root = new BNode();
			root.insert(value);
		} else {
			root.insert(value);
		}
	} // end insert()
} // end BTree