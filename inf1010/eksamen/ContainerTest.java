class ContainerTest {
	public static void main(String[] args) {
		GenContainer<Car> garage = new GenContainer<Car>();
		garage.putItemFIFO(new Car("Ferrari", 2004));
		garage.putItemFIFO(new Car("Hyundai", 1997));
		garage.putItemFIFO(new Car("VolksWagen", 1993));
		garage.putItemFIFO(new Car("Bugatti", 2007));
		garage.putItemFIFO(new Car("Audi", 2010));
		garage.putItemFIFO(new Car("Audi", 1978));
		garage.putItemFIFO(new Car("Peugeot", 2002));

		garage.removeFirst();
		garage.removeLast();

		for (Car c : garage) {
			c.printInfo();
		}
	}
}


class Car {
	private String make;
	private int year;

	Car(String make, int year) {
		this.make = make;
		this.year = year;
	}

	public void printInfo() {
		System.out.println(make + ", " + year);
	}
}