class Oppgave5 {
	public static void main(String[] args) {
		System.out.println("Oppgave5");		
	}
}

class BinNode {
	int data;
	BinNode left;
	BinNode right;
}

class BinTree {
	int number(BinNode t) {
		if (t == null) 
			return 0;
		else 
			return 1 + number(t.left) + number(t.right);
	}

	int sum(BinNode t) {
		if (t == null)
			return 0;
		else
			return t.data + sum(t.left) + sum(t.right);
	}
}