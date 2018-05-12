class Oblig1 {
	public static void main(String[] args) {
		Test t = new Test();
	}
}

class Test {

	//Tesklassen lager personer og setter de opp slik det er bedt om
	Test() {
		Person[] personer = new Person[4];
		personer[0] = new Person("Eirik", 3);
		personer[1] = new Person("Ask", 3);
		personer[2] = new Person("Dana", 3);
		personer[3] = new Person("Tom", 3);

		personer[0].blirKjentMed(personer[1]);
		personer[0].blirKjentMed(personer[2]);
		personer[0].blirKjentMed(personer[3]);

		personer[1].blirKjentMed(personer[0]);
		personer[1].blirKjentMed(personer[2]);
		personer[1].blirKjentMed(personer[3]);
		personer[1].blirUvennMed(personer[2]);
		personer[1].blirUvennMed(personer[3]);
		personer[1].blirForelsketI(personer[0]);

		personer[2].blirKjentMed(personer[1]);
		personer[2].blirKjentMed(personer[3]);
		personer[2].blirKjentMed(personer[0]);
		personer[2].blirUvennMed(personer[0]);
		personer[2].blirForelsketI(personer[3]);

		personer[3].blirKjentMed(personer[0]);
		personer[3].blirKjentMed(personer[1]);
		personer[3].blirKjentMed(personer[2]);
		personer[3].blirUvennMed(personer[0]);
		personer[3].blirUvennMed(personer[1]);
		personer[3].blirForelsketI(personer[2]);

		for (Person p : personer) {
			p.skrivUtAltOmMeg();
		}
	}

}

class Person {
	private String navn;
	private Person[] kjenner;
	private Person[] likerIkke;
	// venner er alle kjente minus likerIkke
	private Person forelsketI;
	private Person sammenMed;

	Person(String n, int lengde) {
		navn = n;
		kjenner = new Person[lengde];
		likerIkke = new Person[lengde];
	}

	public String hentNavn() { return navn; }

	public Boolean erKjentMed(Person p) {
		// returnerer true om personen er funnet i kjenner[]
		for (Person x : kjenner) {
			if(x.equals(p)) return true;
		}
		return false;
	}

	public void blirKjentMed(Person p) {
		// sjekker om det er "deg selv"
		if (p.hentNavn() != navn) {
			// Finner foerste ledige plass og fyller inn
			for (int i = 0; i < kjenner.length; i++) {
				if (kjenner[i] == null) {
					kjenner[i] = p;
					break;
				}
			}
		}
	}

	public void blirForelsketI(Person p) {
		// sjekker om det er "deg selv"
		if (p.hentNavn() != navn) {
			forelsketI = p;
		}
	}

	public void blirUvennMed(Person p) {
		// Sjekker om det er "deg selv"
		if (p.hentNavn() != navn) {
			// Finner foerste ledige plass og fyller inn
			for (int i = 0; i < likerIkke.length; i++) {
				if (likerIkke[i] == null) {
					likerIkke[i] = p;
					break;
				}
			}
		}
	}

	public boolean erVennMed(Person p) {
		// Sjekker om det er en uvenn
		for (Person x : likerIkke) {
			if(x.equals(p)) return false;
		}
		// Saa om de er kjente
		for (Person x : kjenner) {
			if(x.equals(p)) return true;
		}
		// Returnerer false om ingen over trigges
		return false;
	}

	public void blirVennMed(Person p) {
		// sjekker om det er "deg selv"
		if (p.hentNavn() != navn) {
			// Sjekker om de er kjent
			if(erKjentMed(p)) {
				// isaafall fjerner fra likerIkke
				for (Person x : likerIkke) {
					if (x.equals(p)) {
						x = null;
						break;
					}
				}				
			} else {
				// Hvis ikke blir de kjent
				blirKjentMed(p);
			}
		}
	}

	public void skrivUtVenner() {
		// Henter alle venner
		Person[] venner = hentVenner();
		for (Person p : venner) {
			if (p != null) {
				// Skriver ut alle venner
				System.out.print(p.hentNavn() + " ");
			}
			System.out.println();
		}
	}

	public Person hentBestevenn() {
		// Sjekker om kjenner[0] er en venn
		if(erVennMed(kjenner[0])) {
			return kjenner[0];
		} else {
			return null;
		}
	}

	public Person[] hentVenner() {
		// Gaar gjennom alle kjente og legger til venner
		Person[] venner = new Person[kjenner.length];
		int vennNr = 0;
		for (int i = 0; i < kjenner.length; i++) {
			if(erVennMed(kjenner[i])) {
				venner[vennNr++] = kjenner[i];
			}
		}
		return venner;
	}

	public int antVenner() {
		// Teller antall ikke-null-objekter i venner[]
		int antall = 0;
		Person[] venner = hentVenner();
		for (Person p : venner) {
			if (p != null) {
				antall++;
			}
		}
		return antall;
	}
	
	public  void skrivUtKjenninger() {
		for (Person p : kjenner) {
			if (p != null) {
				System.out.print(p.hentNavn() + " ");
			}
		}
		System.out.println("");
	}

	public void skrvUtLikerIkke() {
		for (Person p : likerIkke) {
			if (p != null) {
				System.out.print(p.hentNavn() + " ");
			}
		}
		System.out.println("");
	}

	public void skrivUtAltOmMeg() {
		System.out.print(navn + " kjenner: ");
		skrivUtKjenninger();
		if (forelsketI != null) {
			System.out.println(navn + " er forelsket i " + forelsketI.hentNavn());
		}
		System.out.print(navn + " liker ikke ");
		skrvUtLikerIkke();
	}
 
}