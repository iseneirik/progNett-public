class Oppgave6 {
	public static void main(String[] args) {
		System.out.println("Oppgave6");
	}
}

class BinNode {
	int data;
	BinNode left;
	BinNode right;

	BinNode(int data) {
		this.data = data;
	}

	int number() {
		int number = 0;
		if (left != null) number += left.number();
		if (right != null) number += right.number();
		return number + 1;
	}

	int sum() {
		int sum = 0;
		if (left != null) sum += left.sum();
		if (right != null) sum += right.sum();
		return sum + data;
	} 
}