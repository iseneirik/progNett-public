import java.util.Scanner;

class Legekontor {
	private static final String DEFAULT = "data.txt";
	private final String FILNAVN;
	private FilLeser filLeser;
	private FilSkriver filSkriver;
	private Scanner termLeser;

	// Lister og tabeller for Legekontoret
	SortertEnkelListe<Lege> legeListe;
	Tabell<Pasient> pasientTabell;
	Tabell<Legemiddel> legemiddelTabell;
	EnkelReseptListe resepter;

	Legekontor() {
		// Kall Constructor med "data.txt"
		this(DEFAULT);
	} // end Constructor

	Legekontor(String filnavn) {
		FILNAVN = filnavn;
		filLeser = new FilLeser(FILNAVN);
		filSkriver = new FilSkriver(this);
		termLeser = new Scanner(System.in);

		// Les inn data fra FILNAVN inn i listene og tabellene
		pasientTabell = filLeser.lesInnPasienter();
		legeListe = filLeser.lesInnLeger();
		legemiddelTabell = filLeser.lesInnLegemidler();
		resepter = filLeser.lesInnResepter(this);

		// Fordel reseptene til leger og pasienter
		for (Resept r : resepter) {
			r.getLege().reseptListe.settInn(r);
			pasientTabell.hent(r.getPasientId()).reseptListe.settInn(r);
		}

		// Alt data er lest inn fra fil, start programloopen
		programLoop();
	} // end Constructor

	private void programLoop() {
		boolean aktiv = true;
		
		// Programmet kjoerer til aktiv er gjort false
		while (aktiv) {
			// skriv menyen for hver loop
			skrivMeny();
			// faa et gyldig valg
			int valg = valgPrompt();
			System.out.println("-------------------------------------------------------");

			// send bruker videre etter valg
			switch (valg) {
				case 1:
					opprettLegemiddel();
					break;
				case 2:
					opprettLege();
					break;
				case 3:
					opprettPasient();
					break;
				case 4:
					opprettResept();
					break;
				case 5:
					skrivUtLegemiddelFraResept();
					break;
				case 6:
					skrivUtLegemidler();
					break;
				case 7:
					skrivUtLeger();
					break;
				case 8:
					skrivUtPasienter();
					break;
				case 9:
					skrivUtResepterPerson();
					break;
				case 10:
					skrivUtNarkotikaStatistikk();
					break;
				case 11:
					skrivUtVanedannendeStatistikk();
					break;
				case 12:
					avsluttProgram();
					aktiv = false;
					break;
			}
		}
	} // end ProgramLoop

	private void skrivUtLegemiddelFraResept() {
		// Be om reseptnummer
		int reseptNr = -1;
		while (reseptNr < 0 ) {
			System.out.print(" Angi reseptnummer: ");
			reseptNr = hentInt();
		}
		// Finn resepten, skriv til skjerm om ikke funnet
		Resept resept = null;
		try {
			resept = resepter.finnResept(reseptNr);
		} catch (ReseptIkkeFunnet e) {
			System.out.println(" Resepten ble ikke funnet!");
			return;
		}

		// Om resepten er funnet, og har reit, skriv ut info
		if (!resept.erGyldig()) {
			System.out.println(" Resepten er ikke lenger gyldig!");
		} else {
			System.out.printf("%nLegemiddel:%n%s%n", resept.getMiddel().hentInfo());
			System.out.printf("Lege: %s%n", resept.getLege().getNavn());
			System.out.printf("Pasient: %s%n", pasientTabell.hent(resept.getPasientId()).getNavn());
			System.out.printf("%nBelop aa betale: %d,-%n", resept.getPris());
		}
	} // end skrivUtLegemiddelFraResept



	private void skrivUtVanedannendeStatistikk() {
		int vanedannendeResepterKvinner = 0;
		int vanedannendeResepterMenn = 0;

		for (Pasient pasient : pasientTabell) {
			// tell antall resepter paa vanedannende midler
			int vanedannendeResepter = 0;
			for (Resept resept : pasient.reseptListe) {
				if (resept.middel.isTypeB()) vanedannendeResepter++;
			}

			// Skriv til skjerm antall resepter pasient har som er vanedannende
			System.out.println(pasient.getNavn() + " har faatt utskrevet " + vanedannendeResepter +
							   " resepter for vanedannende legemidler.");

			// legg til paa statisktikk for kvinne eller mann
			if (pasient.erKvinne()) vanedannendeResepterKvinner += vanedannendeResepter;
			else vanedannendeResepterMenn += vanedannendeResepter;
		}
		int total = vanedannendeResepterMenn + vanedannendeResepterKvinner;

		// Skriv statistikk til skjerm
		System.out.println("Det er skrevet ut " + total + " resepter for vanedannende legemidler.");
		System.out.println(vanedannendeResepterKvinner + " til kvinner, og " + vanedannendeResepterMenn + " til menn.");
	} // end skrivUtVanedannendeStatistikk


	private void skrivUtNarkotikaStatistikk() {
		for (Lege lege : legeListe) {
			// Om lege er en avtalelege, finn og skriv statistikk
			if (lege instanceof AvtaleLege) {
				int teller = 0;
				// tell opp alle resepter paa legemidler av type A
				for (Resept resept : lege.reseptListe) {
					if (resept.middel.isTypeA()) teller++;
				}
				System.out.println(lege.navn + " har skrevet ut " + teller + " resepter for narkotiske legemidler.");
			}
		}
	} // end skrivUtNarkotikaStatistikk


	private void skrivUtResepterPerson() {
		System.out.println(" *** skriv ut resept for en bestemt person *** ");

		// finn en gyldig pasient
		Pasient pasient = null;
		while (pasient == null) {
			System.out.print(" Vennligst oppgi pasientID: ");
			int persNr = hentInt();
			termLeser.nextLine();

			pasient = pasientTabell.hent(persNr);
		}

		System.out.println();

		// skriv alle resepter i personens reseptliste
		for (Resept r : pasient.reseptListe) {
			System.out.println(r.hentInfo());
		}

	} // end skrivUtResepterPersoner

	private void skrivMeny() {
		System.out.println("-------------------------------------------------------");
		System.out.println("  1: Opprett et nytt legemiddel");
		System.out.println("  2: Opprett en ny lege");
		System.out.println("  3: Opprett en ny pasient");
		System.out.println("  4: Opprett en ny resept");
		System.out.println("  5: Hente legemiddel fra resept");
		System.out.println("  6: Skriv ut alle legemidler");
		System.out.println("  7: Skriv ut alle leger");
		System.out.println("  8: Skriv ut alle pasienter");
		System.out.println("  9: Skriv ut resepter for en gitt person");
		System.out.println(" 10: Skriv ut statistikk for narkotiske legemidler utskrevet av avtaleleger");
		System.out.println(" 11: Skriv ut statistikk over bruk av vanedannende resepter");
		System.out.println(" 12: Avslutt program");
		System.out.println("-------------------------------------------------------");
	} // end skrivMeny

	private int valgPrompt() {
		int valg = 0;
		// fortsett aa spoer etter heltall fram til valget er gyldig
		while (valg < 1 || valg > 12) {
			System.out.print(" Vennligst velg et alternativ (valg + enter): ");
			valg = hentInt();
		}
		termLeser.nextLine();
		return valg;
	} // end ValgPrompt

	public int hentInt() {
		// Tvinger bruker til aa skrive heltall, hindrer crash
		while (!termLeser.hasNextInt()) {
			System.out.print(" Skriv inn et heltall: ");
			termLeser.nextLine();
		}
		return termLeser.nextInt();
	} // end hentInt

	private void opprettLegemiddel() {
		System.out.println(" *** Opprett et legemiddel ***");

		// Henter all info som trengs, oppretter objekt, og legger i tabellen
		System.out.print(" Navn: ");
		String navn = termLeser.nextLine();

		System.out.print(" Pris: ");
		int pris = hentInt();
		termLeser.nextLine();

		char type = 'x';
		while (type != 'a' && type != 'b' && type != 'c') {
			System.out.print(" Type (a/b/c): ");
			type = termLeser.nextLine().toLowerCase().charAt(0);
		}

		int styrke = 0;
		if (type != 'c') {
			System.out.print(" Styrke: ");
			styrke = hentInt();
		}

		String form = "";
		while (!form.equalsIgnoreCase(Legemiddel.PILLE) && !form.equalsIgnoreCase(Legemiddel.INJEKSJON) && !form.equalsIgnoreCase(Legemiddel.LINIMENT)) {
			System.out.print(" Form (liniment/pille/injeksjon): ");
			form = termLeser.next();
		}

		int mengde = -1;

		while (mengde <= 0) {
			if (form.equalsIgnoreCase(Legemiddel.PILLE)) System.out.print(" Antall piller i eske: ");
			else if (form.equalsIgnoreCase(Legemiddel.INJEKSJON)) System.out.print(" Dosering av virkemiddel: ");
			else System.out.print(" Mengde i pakke (cm3): ");
			mengde = hentInt();
		}

		Legemiddel nyttMiddel = null;
		if (form.equalsIgnoreCase(Legemiddel.PILLE)) {
			nyttMiddel = new LegemiddelPille(navn, pris, type, styrke, mengde);
		} else if (form.equalsIgnoreCase(Legemiddel.LINIMENT)) {
			nyttMiddel = new LegemiddelLiniment(navn, pris, type, styrke, mengde);
		} else if (form.equalsIgnoreCase(Legemiddel.INJEKSJON)) {
			nyttMiddel = new LegemiddelInjeksjon(navn, pris, type, styrke, mengde);
		}

		legemiddelTabell.settInn(nyttMiddel, nyttMiddel.getId());
	} // end OprettLegemiddel

	private void opprettLege() {
		System.out.println(" *** Opprett en lege *** ");

		// samme som OpprettLegemiddel
		System.out.print(" Navn: ");
		String navn = termLeser.nextLine();

		char spesialist = ' ';
		while (spesialist != 'y' && spesialist != 'n') {
			System.out.print(" Spesialist (y/n): ");
			spesialist = termLeser.next().toLowerCase().charAt(0);
		}
		boolean erSpesialist = (spesialist == 'y' ? true : false);

		char avtaleLege = ' ';
		while (avtaleLege != 'y' && avtaleLege != 'n') {
			System.out.print(" avtalelege (y/n): ");
			avtaleLege = termLeser.next().toLowerCase().charAt(0);
		}

		if (avtaleLege == 'y') {
			legeListe.settInn(new AvtaleLege(navn, erSpesialist));
		} else {
			legeListe.settInn(new Lege(navn, erSpesialist));
		}
	} // end opprettLege

	private void opprettPasient() {
		System.out.println(" *** Opprett en pasient *** ");

		// Samme som OpprettLegemiddel
		System.out.print(" Navn: ");
		String navn = termLeser.nextLine();

		char kjonn = ' ';
		while (kjonn != 'm' && kjonn != 'k') {
			System.out.print(" Kjonn (m/k): ");
			kjonn = termLeser.nextLine().toLowerCase().charAt(0);
		}

		Pasient nyPasient = new Pasient(navn, kjonn);
		boolean sattInn = pasientTabell.settInn(nyPasient, nyPasient.getId());
		System.out.println(sattInn);
	} // end oprettPasient

	private void opprettResept() {
		System.out.println(" *** Opprett en resept *** ");

		// Samme som OpprettLegemiddel
		Legemiddel legemiddel = null;
		while (legemiddel == null) {
			System.out.print(" LegemiddelID: ");
			int id = hentInt();
			termLeser.nextLine();
			legemiddel = legemiddelTabell.hent(id);
		}

		Lege lege = null;
		while (lege == null) {
			System.out.print(" Navn paa lege: ");
			String navn = termLeser.nextLine();
			lege = legeListe.hent(navn);
		}

		Pasient pasient = null;
		while (pasient == null) {
			System.out.print(" PasientID: ");
			int id = hentInt();
			termLeser.nextLine();
			pasient = pasientTabell.hent(id);
		}

		System.out.print(" Antall reit: ");
		int reit = hentInt();
		termLeser.nextLine();

		Resept nyResept;
		while (true) {
			System.out.print(" Type (hvit/blaa): ");
			char type = termLeser.nextLine().toLowerCase().charAt(0);
			if (type == 'b') {
				nyResept = new BlaaResept(legemiddel, lege, pasient.getId(), reit);
				break;
			} else if (type == 'h') {
				nyResept = new HvitResept(legemiddel, lege, pasient.getId(), reit);
				break;
			}
		}

		// Setter inn resepter i listen til legekontor, pasient og lege
		resepter.settInn(nyResept);
		pasient.reseptListe.settInn(nyResept);
		lege.reseptListe.settInn(nyResept);
	} // end oprettResept


	private void skrivUtPasienter() {
		System.out.println(" *** skriver ut pasienter *** ");
		for (Pasient p : pasientTabell) {
			System.out.println(p.hentInfo());
		}
	} // end skrivUtPasienter

	private void skrivUtLeger() {
		System.out.println(" *** skriver ut leger *** ");
		for (Lege l : legeListe) {
			System.out.println(l.hentInfo());
		}
	} // end skrivUtLeger

	private void skrivUtLegemidler() {
		System.out.println(" *** skriver ut legemidler *** ");
		for (Legemiddel l : legemiddelTabell) {
			System.out.println(l.hentInfo());
		}
	} // end skrivUtLegemidler

	private void avsluttProgram() {
		filSkriver.write();
		System.out.println(" Programmet avsluttes! ");
	} // end avsluttProgram
} // end Legekontor