import java.util.*;
import java.io.*;

class Oblig3A {
    public static void main(String[] args) {
	// Sjekk om bruker har lagt ved filnavn
	if (args.length < 1) {
	    System.out.println("Bruk: > java Oblig3A <tekstfil>");
	} else {
	    Tekstanalyse t = new Tekstanalyse();
	    t.analyser(args[0]);
	}
    }// end main
}// end Oblig3A



class Tekstanalyse {

    void analyser(String filnavn) {
	try {
	    //Aapne fil med scanner
	    Scanner tekst = new Scanner(new File(filnavn));

	    //Oppsummer teksten
	    oppsummerTekst(tekst);

	    //Lukk filen
	    tekst.close();
	} catch(Exception e) {
	    e.printStackTrace();
	}
    }// end analyser

    void oppsummerTekst(Scanner tekst) {
	//Variabler for lagring av analyse
	String[] ord = new String[5000];
	int[] antUnikeOrd = new int[5000];
	int ant = 0;
	int antUnik = 0;

	while(tekst.hasNext()) {
	    // Ordet som sjekkes
	    String currOrd = tekst.next();

	    for(int i = 0; i < ord.length; i++) {
		// Om den finner en tom plass, legg inn ordet
		if(ord[i] == null) {
		    ord[i] = currOrd;
		    antUnikeOrd[i] = 1;
		    // Antall unike ord oekes med en
		    antUnik++;
		    break;
		} else if(ord[i].equalsIgnoreCase(currOrd)) {
		    // Finnes ordet en annen plass, antall++ paa tilsvarende indeks
		    antUnikeOrd[i]++;
		    break;
		} 
	    }
	    // Antall ord oekes med en
	    ant++;
	}
	// Bruk data funnet til aa skrive oppsummering til en ny tekstfil
	skrivOppsummering(ord, antUnikeOrd, ant, antUnik);
    }// end tekstOppsummering

    void skrivOppsummering(String[] ord, int[] antUnikeOrd, int ant, int antUnik) {
	try {
	    // Aapne en ny fil aa skrive til
	    FileWriter oppsumm = new FileWriter(new File("oppsummering.txt"));
	    oppsumm.write("Antall ord lest: " + ant + " og antall unike ord: " + antUnik + ".\n\n");
	    
	    for(int i = 0; i < ord.length; i++) {
		//Skriv til fil fram til det er tomt for unike ord
		if(ord[i] == null) {
		    break;
		} else {
		    oppsumm.write(String.format("%-25s %d %n", ord[i], antUnikeOrd[i]));
		}
	    }
	    oppsumm.close();

	} catch(Exception e) {
	    e.printStackTrace();
	}
    }// end skrivOppsummering
}// end Tekstanalyse