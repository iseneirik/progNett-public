class Test {
	public static void main(String[] args) {
		SimpMath s = new SimpMath();

		int[] x = {2240,4202,224,62};
		int[] y = {990,1235,3405,234};

		s.printArray(x);
		s.printArray(y);
		s.printArray(s.divArr(x,2));
	}
}

class SimpMath {
	
	int[] divArr(int[] x, int y) {
		long carry = 0;
		for (int i = 0; i < x.length; i++) {
			// Make substitute for x[i]
			int sub = x[i];
			// Divide x[i] by y
			x[i] = (int)((x[i] + carry)/y);
			// Amount that "didn't fit" is carried to next division
			carry = sub - (y * x[i]);
			carry *= 10000;
		}
		return x;
	}

	int[] mulArr(int[] x, int y) {
		int carry = 0;
		for (int i = x.length-1; i >= 0; i--) {
			// Multiply x[i] with y and add carry from x[i+1]
			x[i] = (x[i] * y) + carry;
			// Make x[i] max 4 digits
			x[i] = x[i] % 10000;
			// Make carry save "excessive digits"
			carry = x[i] / 10000;
		}
		return x;
	}
	
	int[] addArr(int[] x, int[] y) {
		int carry = 0;
		for (int i = x.length-1; i >= 0; i--) {
			x[i] += (y[i] + carry);
			// Carry increases by 1 for every 10k
			carry = x[i]/10000;
			// Make x[i] maximum 4 digits
			x[i] -= carry*10000;
		}
		return x;
	}

	int[] subArr(int[] x, int[] y) {
		int carry = 0;
		for (int i = x.length-1; i >= 0; i--) {
			// Subtract y[i] and the amount carried to x[i+1]
			x[i] -= (y[i] + carry);
			// Reset the carry
			carry = 0;
			// Make x[i] positive, and save carried amount
			while(x[i] < 0) {
				x[i] += 10000;
				carry += 1;
			}
		}
		return x;
	}

	void printArray(int[] x) {
		for (int z : x) {
			System.out.printf("%04d", z);
		}
		System.out.println();
	}

}

// System.out.printf("%04d", x[i]);