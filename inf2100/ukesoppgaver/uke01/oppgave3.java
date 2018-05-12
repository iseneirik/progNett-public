class Fib {
	final static int n = 40;

	static int fib1(int x) {
		int f1 = 0, f2 = 1;

		for (int i = 0; i < x; i++) {
			int f3 = f1 + f2;
			f1 = f2; 
			f2 = f3;
		}
		return f1;
	}

	static int fib2(int x) {
		if (x <= 2)
			return 1;
		else
			return fib2(x-2) + fib2(x-1);
	}

	public static void main(String arg[]) {
		System.out.println("fib1(" + n + ") = " + fib1(n));
		System.out.println("fib2(" + n + ") = " + fib2(n));
	}
}