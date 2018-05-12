class Oppgave4 {
	public static void main(String[] args) {
		ArrayStack<Plate> p = new ArrayStack<Plate>();
		p.push(new Plate());
		p.push(new Plate());
		p.push(new Plate());
		p.push(new Plate());
		p.push(new Plate());
		p.push(new Plate());
		System.out.println(p.retNum());
		p.pop();
		p.pop();
		System.out.println(p.retNum());
		p.top();
		System.out.println(p.retNum());
	}
}

interface Stack<T> {
	public T pop();
	public T top();
	public void push(T t);
	public boolean isEmpty();
}

class Plate {

}

class ArrayStack<T> implements Stack<T> {
	private T[] tArr = (T[])new Object[10];
	private int numT = 0;

	public void push(T t) {
		tArr[numT++] = t;
	}

	public T pop() {
		T t;
		if (numT > 0) {
			t = tArr[--numT];
			tArr[numT] = null;
		} else {
			t = null;
		}
		return t;
	}

	public T top() {
		if (numT > 0) {
			return tArr[numT-1];
		} else {
			return null;
		}
	}

	public boolean isEmpty() {
		return (numT == 0 ? true : false);
	}

	public int retNum() {
		return numT;
	}
}