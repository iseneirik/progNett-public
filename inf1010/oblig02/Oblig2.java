class Oblig2 {
	public static void main(String[] args) {
		Test t = new Test();
	} // end main
} // end Oblig2

class Test {
	// Arrayer for aa holde paa testgjenstander
	private Bok[] gaveBok = new Bok[21];
	private Plate[] gavePlate = new Plate[21];
	private Person[] personer = new Person[7];

	//Tesklassen lager personer og setter de opp slik det er bedt om
	Test() {

		gaveBok[0] = new Bok("Tolkien", "LotR", 1954);
		gaveBok[1] = new Bok("Robert Jordan", "Wheel of Time", 1980);
		gaveBok[2] = new Bok("Douglas Adams", "Hitchhikers guide to the galaxy", 1980);
		gaveBok[3] = new Bok("Gammel", "Bok", 1834);
		gaveBok[4] = new Bok("Eldre", "Bok1", 1820);
		gaveBok[5] = new Bok("Eldst", "Bok2", 1760);
		gaveBok[6] = new Bok("EldstZ", "Bok3", 1760);
		gaveBok[7] = new Bok("EldstZZ", "Bok4", 1760);
		gaveBok[8] = new Bok("EldstZZZ", "Bok5", 1760);
		gaveBok[9] = new Bok("EldstZZZZ", "Bok6", 1760);
		gaveBok[10] = new Bok("EldstZZZZZ", "Bok7", 1760);
		gaveBok[11] = new Bok("EldstZZZZZZ", "Bok8", 1760);
		gaveBok[12] = new Bok("EldstZZZZZZZ", "Bok9", 1760);
		gaveBok[13] = new Bok("EldstZZZZZZZZ", "Bok10", 1760);
		gaveBok[14] = new Bok("EldstZZZZZAZ", "Bok11", 1760);
		gaveBok[15] = new Bok("EldstZZZZZAAZ", "Bok12", 1760);
		gaveBok[16] = new Bok("EldstZZZZZBZ", "Bok13", 1760);
		gaveBok[17] = new Bok("EldstZZZZZBBZ", "Bok14", 1760);
		gaveBok[18] = new Bok("EldstZZZZZCZ", "Bok15", 1760);
		gaveBok[19] = new Bok("EldstZZZZZCCZ", "Bok16", 1760);
		gaveBok[20] = new Bok("EldstZZZZZDZ", "Bok17", 1760);


		gavePlate[0] = new Plate("Silya Nymoen", "Silya Nymoen", 7);
		gavePlate[1] = new Plate("For Today", "Immortal", 9);
		gavePlate[2] = new Plate("Nero", "Welcome Reality", 13);
		gavePlate[3] = new Plate("Queen", "Queen", 9);
		gavePlate[4] = new Plate("Queen", "Bohemian Rhapsody", 1);
		gavePlate[5] = new Plate("Queen", "Best of Queen", 11);
		gavePlate[6] = new Plate("Queen", "Best of Queen1", 11);
		gavePlate[7] = new Plate("Queen", "Best of Queen2", 11);
		gavePlate[8] = new Plate("Queen", "Best of Queen3", 11);
		gavePlate[9] = new Plate("Queen", "Best of Queen4", 11);
		gavePlate[10] = new Plate("Queen", "Best of Queen5", 11);
		gavePlate[11] = new Plate("Queen", "Best of Queen6", 11);
		gavePlate[12] = new Plate("Queen", "Best of Queen7", 11);
		gavePlate[13] = new Plate("Queen", "Best of Queen8", 11);
		gavePlate[14] = new Plate("Queen", "Best of Queen9", 11);
		gavePlate[15] = new Plate("Queen", "Best of Queen10", 11);
		gavePlate[16] = new Plate("Queen", "Best of Queen11", 11);
		gavePlate[17] = new Plate("Queen", "Best of Queen12", 11);
		gavePlate[18] = new Plate("Queen", "Best of Queen13", 11);
		gavePlate[19] = new Plate("Queen", "Best of Queen14", 11);
		gavePlate[20] = new Plate("Queen", "Best of Queen15", 11);

		personer[0] = new Person("Eirik", 0);
		personer[0].samlerAv("Boker", 5);
		personer[0].megetInteressertI(1946);
		personer[0].samlerAv("Plater", 5);
		personer[0].megetInteressertI("Queen");

		personer[1] = new Person("Tomas Kristoffer", 0);
		personer[1].samlerAv("Boker", 5);
		personer[1].samlerAv("Plater", 5);
		personer[1].megetInteressertI("Silya Nymoen");
		
		personer[2] = new Person("Gunnar", 0);
		personer[2].samlerAv("Boker", 5);
		personer[2].megetInteressertI(1900);
		personer[2].samlerAv("Plater", 5);
		
		personer[3] = new Person("Haakon", 0);
		personer[3].samlerAv("Plater", 5);
		personer[3].megetInteressertI("Bob Dylan");

		personer[4] = new Person("Lise", 0);
		personer[4].samlerAv("Plater", 5);
		
		personer[5] = new Person("Marte", 0);
		personer[5].samlerAv("Boker", 5);
		
		personer[6] = new Person("Maren", 0);

		// Del ut gaver
		delUtGaverBok();
		delUtGaverPlate();

		// Skriv ut alt om hver person
		for (Person p : personer) {
			p.skrivUtAltOmMeg();
		}
		
		System.out.println("********************************");

		// Skriv ut resten av det som er igjen
		skrivUtRestBok();
		skrivUtRestPlate();
	} // end Constructor

	public void delUtGaverBok() {
		for (int i = 0; i < gaveBok.length; i++) {
			for (Person p : personer) {
				gaveBok[i] = p.vilDuHaGaven(gaveBok[i]);
				if (gaveBok[i] == null) { break; }
			}
		}
	} // end delUtGaverBok

	public void skrivUtRestBok() {
		for (int i = 0;	i < gaveBok.length; i++) {
			if (gaveBok[i] != null) {
				System.out.println("Ingen ville ha: " + gaveBok[i].getForfatter() + " - " + gaveBok[i].getTittel());		
				gaveBok[i] = null;
			}
		}	
	} // end skrivUtRestBok

	public void delUtGaverPlate() {
		for (int i = 0; i < gavePlate.length; i++) {
			for (Person p : personer) {
				gavePlate[i] = p.vilDuHaGaven(gavePlate[i]);
				if (gavePlate[i] == null) { break; }
			}
		}
	} // end delUtGaverPlate

	public void skrivUtRestPlate() {
		for (int i = 0;	i < gavePlate.length; i++) {
			if (gavePlate[i] != null) {
				System.out.println("Ingen ville ha: " + gavePlate[i].getArtist() + " - " + gavePlate[i].getTittel());		
				gavePlate[i] = null;
			}
		}	
	} // end skrivUtRestPlate

} // end Test

class Person {
	private String navn;
	private Person[] kjenner;
	private Person[] likerIkke;

	// venner er alle kjente minus likerIkke
	private Person forelsketI;
	private Person sammenMed;

	// array for plater/boeker
	private Bok[] boksamling;
	private Plate[] platesamling;

	// ant plater/boeker i arrayen
	private int antBok = 0;
	private int antPlate = 0;

	// spesiell interesse
	private int spessAar;
	private String spessArtist;

	Person(String n, int lengde) {
		navn = n;
		kjenner = new Person[lengde];
		likerIkke = new Person[lengde];
	} // end constructor

	public String hentNavn() { return navn; }
	
	public void samlerAv (String smlp, int ant) {
		// sjekker hva som skal samles paa, oppdaterer boolean og lager array
		if (smlp.equalsIgnoreCase("boker")) {
			boksamling = new Bok[ant];
		} else if (smlp.equalsIgnoreCase("plater")) {
			platesamling = new Plate[ant];
		}
	} // end samlerAv

	public void megetInteressertI(String artist) {
		// sjekker som det samles paa, saa setter interesse
		if (platesamling != null) {
			spessArtist = artist;
		}
	} // end megetInteressertI

	public void megetInteressertI(int eldreEnn) {
		// sjekker som det samles paa, saa setter interesse
		if (boksamling != null) {
			spessAar = eldreEnn;
		}
	} // end megetInteressertI

	public void putBok(Bok b) {
		boksamling[antBok++] = b;
	} // end putBok

	public void putPlate(Plate p) {
		platesamling[antPlate++] = p;
	} // end putPlate

	public boolean harGaven(Bok b) {
		for (Bok x : boksamling) {
			if (x != null && x.getTittel().equalsIgnoreCase(b.getTittel())) {
				return true;
			}
		}
		return false;
	} // end harGaven

	public boolean interessert(Bok b) {
		if (b.getAar() < spessAar && spessAar != 0) {
			return true;
		} else {
			return false;
		}
	} // end interessert

	public Bok vilDuHaGaven(Bok b) {
		if (boksamling == null) { return b; }
		if (harGaven(b)) { return b; }

		// Om minst halvparten er ledig, legg inn
		if (antBok <= boksamling.length / 2) {
			putBok(b);
			return null;
		} 
		// Om ikke halvparten er ledig, sjekk om spesielt interessert
		if (interessert(b)) {
			// Ved ledig plass, legg inn
			if (antBok < boksamling.length) {
				putBok(b);
				return null;
			}
			// Ved helt full boksamling, bytt ut
			if (antBok == boksamling.length) {
				for (int i = 0; i < boksamling.length; i++) {
					if(!interessert(boksamling[i])) {
						Bok y = boksamling[i];
						boksamling[i] = b;
						return y;
					}
				}
			}
		}
		return b;
	} // end vilDuHaGaven

	public boolean harGaven(Plate p) {
		for (Plate x : platesamling) {
			if(x != null && x.getTittel().equalsIgnoreCase(p.getTittel())) {
				return true;
			} 
		}
		return false;
	} // end harGaven

	public boolean interessert(Plate p) {
		if (spessArtist != null && spessArtist.equalsIgnoreCase(p.getArtist())) {
			return true;
		} else {
			return false;
		}
	}

	public Plate vilDuHaGaven(Plate p) {
		if (platesamling == null) { return p; }
		if (harGaven(p)) { return p; }

		// Om minst halvpartern er ledig, legg inn
		if (antPlate <= platesamling.length / 2) {
			putPlate(p);
			return null;
		} 
		// Om ikke halvparten er ledig, sjekk om spesielt interessert
		if (interessert(p)) {
			// Ved ledig plass, legg inn
			if (antPlate < platesamling.length) {
				putPlate(p);
				return null;
			}
			// ved helt full platesamling, bytt ut
			if (antPlate == platesamling.length) {
				for (int i = 0; i < platesamling.length; i++) {
					if(!interessert(platesamling[i])) {
						Plate y = platesamling[i];
						platesamling[i] = p;
						return y;
					}
				}
			}
		}
		return p;
	} // end vilDuHaGaven

	public Boolean erKjentMed(Person p) {
		// returnerer true om personen er funnet i kjenner[]
		for (Person x : kjenner) {
			if(x.equals(p)) return true;
		}
		return false;
	} // end erKjentMed

	public void blirKjentMed(Person p) {
		// sjekker om det er "deg selv"
		if (!p.equals(this)) {
			// Finner foerste ledige plass og fyller inn
			for (int i = 0; i < kjenner.length; i++) {
				if (kjenner[i] == null) {
					kjenner[i] = p;
					break;
				}
			}
		}
	} // end blirKjentMed

	public void blirForelsketI(Person p) {
		// sjekker om det er "deg selv"
		if (!p.equals(this)) {
			forelsketI = p;
		}
	} // end blirForelsketI

	public void blirUvennMed(Person p) {
		// Sjekker om det er "deg selv"
		if (p.equals(this)) {
			// Finner foerste ledige plass og fyller inn
			for (int i = 0; i < likerIkke.length; i++) {
				if (likerIkke[i] == null) {
					likerIkke[i] = p;
					break;
				}
			}
		}
	} // end blirUvennMed

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
	} // end erVennMed

	public void blirVennMed(Person p) {
		// sjekker om det er "deg selv"
		if (!p.equals(this)) {
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
	} // end blirVennMed

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
	} // end skrivUtVenner

	public Person hentBestevenn() {
		// Sjekker om kjenner[0] er en venn
		if(erVennMed(kjenner[0])) {
			return kjenner[0];
		} else {
			return null;
		}
	} // end hentBestevenn

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
	} // end hentVenner

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
	} // end antVenner
	
	
	/***************************************************************** /
	/ Metoder for aa skrive info til skjermen						   /
	/******************************************************************/

	public  void skrivUtKjenninger() {
		for (Person p : kjenner) {
			if (p != null) {
				System.out.print(p.hentNavn() + " ");
			}
		}
		System.out.println("");
	} // end skrivUtKjenninger

	public void skrivUtLikerIkke() {
		for (Person p : likerIkke) {
			if (p != null) {
				System.out.print(p.hentNavn() + " ");
			}
		}
		System.out.println("");
	} // end skrivUtLikerIkke
	
	public void skrivUtInteresse() {
		// Sjekk om det samles paa boeker
		if (boksamling != null) {
			System.out.println("Boeker:");
			// Sjekk om personen har en spess interesse
			if (spessAar != 0) {
				System.out.println("Boeker foer aar: " + spessAar);
			}
			// Sjekk om personen har minst en bok i samlingen
			if (boksamling[0] != null) {	
				System.out.println("Boksamling: ");
			}
			// Skriv ut alle boeker
			for (Bok b : boksamling) {
				if (b != null) {
					System.out.println(b.getTittel() + " - " + b.getForfatter() + " - " + b.getAar());
				}
			}
		} 
		// Samme prossess som med boeker
		if (platesamling != null) {
			System.out.println("Plater:");
			if (spessArtist != null) {
				System.out.println("Plater fra artist: " + spessArtist);
			}
			if (platesamling[0] != null) {
				System.out.println("Platesamling: ");
			}
			for (Plate p : platesamling) {
				if (p != null) {
					System.out.println(p.getTittel() + " - " + p.getArtist() + " - " + p.getSpor() + " spor");
				}
			}
		} if (platesamling == null && boksamling == null) {
			System.out.println("Ingenting");
		}
	} // end skrivUtInteresse

	public void skrivUtAltOmMeg() {
		System.out.println("---------------");
		System.out.print(navn + " kjenner: ");
		skrivUtKjenninger();
		if (forelsketI != null) {
			System.out.println(navn + " er forelsket i: " + forelsketI.hentNavn());
		}
		System.out.print(navn + " liker ikke: ");
		skrivUtLikerIkke();
		System.out.println("Interesser: ");
		skrivUtInteresse();
	} // end skrivUtAltOmMeg
 
} // end Person

class Bok {
	private String forfatter;
	private String tittel;
	private int aar;

	Bok(String forfatter, String tittel, int aar) {
		this.forfatter = forfatter;
		this.tittel = tittel;
		this.aar = aar;
	} // end Constructor

	public String getForfatter() { return forfatter; }
	public String getTittel() { return tittel; }
	public int getAar() { return aar; }
	
} // end Bok

class Plate {
	private String artist;
	private String tittel;
	private int spor;

	Plate(String artist, String tittel, int spor) {
		this.artist = artist;
		this.tittel = tittel;
		this.spor = spor;
	} // end Constructor
	
	public String getArtist() { return artist; }
	public String getTittel() {	return tittel; }
	public int getSpor() { return spor;	}
} // end Plate