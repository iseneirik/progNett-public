class Oppgave4 {
	public static void main(String[] args) {
		new Bachelourstudent("Eirik", "Isene", 20, "INF1010", "UiO");
		new Professor("Mathias", "Johansen", 23, 173.40, "INF1010");
	}
}

abstract class Person {
	protected String fNavn;
	protected String eNavn;
	protected int alder;

	Person(String fNavn, String eNavn, int alder) {
		this.fNavn = fNavn;
		this.eNavn = eNavn;
		this.alder = alder;

		System.out.printf("Navn: %s %s \nAlder: %d \n", fNavn, eNavn, alder);
	}
}

abstract class Student extends Person {
	protected String kurs;
	protected String laerested;
	protected boolean tilgang;

	Student(String fNavn, String eNavn, int alder, String kurs, String laerested) {
		super(fNavn, eNavn, alder);
		this.kurs = kurs;
		this.laerested = laerested;

		System.out.printf("Student tar kurs %s paa %s, ", kurs, laerested);
	}
}

class Bachelourstudent extends Student {
	Bachelourstudent(String fNavn, String eNavn, int alder, String kurs, String laerested)  {
		super(fNavn, eNavn, alder, kurs, laerested);
		tilgang = false;

		System.out.println("er Bachelourstudent");
	}
}

class Masterstudent extends Student {
	Masterstudent(String fNavn, String eNavn, int alder, String kurs, String laerested) {
		super(fNavn, eNavn, alder, kurs, laerested);
		tilgang = true;

		System.out.println("er Masterstudent");
	}
}

class Ansatt extends Person {
	protected double timeslonn;

	Ansatt(String fNavn, String eNavn, int alder, double timeslonn) {
		super(fNavn, eNavn, alder);
		this.timeslonn = timeslonn;

		System.out.printf("lonn: %.2f kr/time \n", timeslonn);
	} 

}

class Forsker extends Ansatt {
	private String fagomraade;

	Forsker(String fNavn, String eNavn, int alder, double timeslonn, String fagomraade) {
		super(fNavn, eNavn, alder, timeslonn);
		this.fagomraade = fagomraade;

		System.out.printf("fagomraade: %s \n", fagomraade);
	}

}

class Professor extends Ansatt {
	private String laererI;

	Professor(String fNavn, String eNavn, int alder, double timeslonn, String laererI) {
		super(fNavn, eNavn, alder, timeslonn);
		this.laererI = laererI;

		System.out.printf("Learer i kurs: %s \n", laererI);
	}
}
