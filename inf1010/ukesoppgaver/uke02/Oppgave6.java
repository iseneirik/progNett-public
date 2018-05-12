class Oppgave6 {
	public static void main(String[] args) {
		Line l = new Line();
		l.put(new Person("Eirik"));
		l.put(new Person("John"));		
		l.put(new Person("Dude"));		
		l.put(new Person("Sweet"));	
		System.out.println(l.get().getName());	
		System.out.println(l.get().getName());	
		System.out.println(l.get().getName());	
		System.out.println(l.get().getName());	
	}
}

class Person {
	private String name;

	Person(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}

interface FIFO {
	public Person get();
	public void put(Person p);
}

class Line implements FIFO {
	Person[] line = new Person[10];
	int numPeople = 0;

	public void put(Person p) {
		if (numPeople < line.length - 1) {
			for (int i = numPeople - 1; i >= 0; i--) {
				line[i+1] = line[i];
			}
			line[0] = p;
			numPeople++;
		}
	}

	public Person get() {
		if (numPeople > 0) {
			return line[--numPeople];
		} else {
			return null;
		}
	}
}