class Oppgave3 {
	public static void main(String[] args) {
		System.out.println("Oppgave3!");
	}
}

class Methods {
	public int[] calculateIndegrees(ListNode l[]) {
		int[] indegrees = new int[l.length];
		for (ListNode node : l) {
			ListNode currNode = l;
			while(currNode.next != null) {
				indegrees[currNode.destination]++;
				currNode = currNode.next;
			}
		}
	}
}