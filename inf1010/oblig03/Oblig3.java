class Oblig3 {
	public static void main(String[] args) {
		Test t = new Test();
	} // end main
} // end Oblig3

class Test {

	Test() {
		// instansier Personer, Gavelager og ListeAvPersoner
		Personer pLager = new Personer();
		GaveLager gLager = new GaveLager();
		ListeAvPersoner pList = new ListeAvPersoner();

		// sett personer inn i ListeAvPersoner
		for(Person p = pLager.hentPerson(); p != null; p = pLager.hentPerson()) {
			pList.settInnSist(p);
		}

		for (int i = 0; i < 500; i++) {
			Gave currGave = gLager.hentGave();
			for (Person p = pList.getListehode().neste; p != null; p = p.neste) {
				currGave = p.vilDuHaGaven(currGave, true);
				if (currGave == null) break;
			}
		}
		pList.skrivAlle();
	} // end Constructor

} // end Test

class Person {
	private String navn;
	private Person[] kjenner = new Person[100];
	private Person[] likerIkke = new Person[10];
	private Person forelsketI;
	private Person sammenMed;
	
	public Person neste;

	// Variabler for gaver
	private String samlerPaa;
	private Gave[] mineGaver;
	private int antGaver = 0;


	Person(String n) {
		navn = n;
	} // end constructor

	public String hentNavn() { return navn; }
	public Person hentSammenMed() { return sammenMed; }


	/***************************************************************** /
	/ Metoder for aa haandtere gaver 								   /
	/******************************************************************/
	
	public void samlerAv (String smlp, int ant) {
		// sjekker hva som skal samles paa og hvor mye
		samlerPaa = smlp;
		mineGaver = new Gave[ant];
	} // end samlerAv

	public void putGave(Gave g) {
		mineGaver[antGaver++] = g;
	} // end putGave

	public Gave vilDuHaGaven(Gave gave, boolean init) {
		// hvis interessert, ta imot gaven
		if (gave.kategori().equals(samlerPaa) && antGaver < mineGaver.length) {
			putGave(gave);
			return null;
		}
		// hvis det er forste gang den tilbys, send videre
		if (init) {
			// Gir gave videre til sammenMed
			if (sammenMed != null) gave = sammenMed.vilDuHaGaven(gave, false);
			if (gave == null) return null;
			// Gir gave videre til forelsketI
			if (forelsketI != null) gave = forelsketI.vilDuHaGaven(gave, false);
			if (gave == null) return null;
			// Gir gave videre til venner
			Person[] venner = hentVenner();
			for (int i = 0; i < antVenner(); i++) {
				gave = venner[i].vilDuHaGaven(gave, false);
				if (gave == null) return null;
			}
		}
		// Hvis ingen tar imot, send tilbake gaven
		return gave;
	} // end vilDuHaGaven
	
	/***************************************************************** /
	/ Metoder for aa endre forhold mellom personer					   /
	/******************************************************************/

	public void blirKjentMed(Person p) {
		// sjekker om det er "deg selv"
		if (p != this) {
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
		if (p != this && !erUvennMed(p)) {
			forelsketI = p;
		}
	} // end blirForelsketI

	public void blirSammenMed(Person p) {
		if (p == this) return;
		if (p == sammenMed) return;

		// holder peker til ekskjereste		
		Person eks = sammenMed;
		sammenMed = p;

		// dersom personen ble sammen med null, return
		if (sammenMed == null) return;
		// dersom personen var sammen med noen, gjor det slutt
		if (eks != null) eks.blirSammenMed(null);
		// gjor kjeresteskapet gjensidig
		p.blirSammenMed(this);
	} // end blirSammenMed

	public void blirUvennMed(Person p) {
		// Sjekker om det er "deg selv"
		if (p != this) {
			// Finner foerste ledige plass og fyller inn
			for (int i = 0; i < likerIkke.length; i++) {
				if (likerIkke[i] == null) {
					likerIkke[i] = p;
					break;
				}
			}
		}
	} // end blirUvennMed
	
	public Boolean erKjentMed(Person p) {
		// returnerer true om personen er funnet i kjenner[]
		for (Person x : kjenner) {
			if(x == p) return true;
		}
		return false;
	} // end erKjentMed

	public boolean erVennMed(Person p) {
		// Sjekker om det er en uvenn
		if (erUvennMed(p)) { return false; }
		// Saa om de er kjente
		if (erKjentMed(p)) { return true; }
		// Returnerer false om ingen over trigges
		return false;
	} // end erVennMed

	public boolean erUvennMed(Person p) {
		for (Person x : likerIkke) {
			if(x == p) return true;
		}
		return false;
	} // end erUvennMed

	public void blirVennMed(Person p) {
		// sjekker om det er "deg selv"
		if (p != this) {
			// Sjekker om de er kjent
			if(erKjentMed(p)) {
				// isaafall fjerner fra likerIkke
				for (Person x : likerIkke) {
					if (x == p) {
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
				System.out.print(p.hentNavn() + ", ");
			}
		}
		System.out.println("");
	} // end skrivUtKjenninger

	public void skrivUtLikerIkke() {
		for (Person p : likerIkke) {
			if (p != null) {
				System.out.print(p.hentNavn() + ", ");
			}
		}
		System.out.println("");
	} // end skrivUtLikerIkke

	public void skrivUtGaver() {
		for (int i = 0;	i < antGaver; i++) {
			System.out.println(mineGaver[i].kategori() + ": " + mineGaver[i].gaveId());
		}
	}

	public void skrivUtAltOmMeg() {
		System.out.println("---------------");
		System.out.println(navn + " kjenner: ");
		skrivUtKjenninger();
		System.out.println(navn + " liker ikke: ");
		skrivUtLikerIkke();
		if (forelsketI != null) 
			System.out.println(navn + " er forelsket i " + forelsketI.hentNavn());
		if (sammenMed != null) 
			System.out.println(navn + " er sammen med " + sammenMed.hentNavn());
		if (antGaver > 0) skrivUtGaver();
	} // end skrivUtAltOmMeg
 
} // end Person