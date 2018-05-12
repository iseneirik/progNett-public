class Oppgave3 {
	public static void main(String[] args) {
		Pollution[] p = new Pollution[10];
		
		for (int i = 0; i < p.length; i++) {
			if (i < 5) {
				p[i] = new Airplane();
			} else {
				p[i] = new Car();
			}
		}

		long total = 0;
		for (Pollution x : p) {
			total += x.emission();
		}

		System.out.println(total);

	}
}

interface Pollution {
	public int emission();
}

class Airplane implements Pollution {
	public int emission() {
		return 100_000_000;
	}
}

class Car implements Pollution {
	public int emission() {
		return 1_000;
	}
}