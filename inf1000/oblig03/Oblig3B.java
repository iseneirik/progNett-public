import java.util.*;
import java.io.*;

class Oblig3B {
    public static void main(String[] args) {
	// Sjekk om bruker har lagt ved filnavn
	if (args.length < 1) {
	    System.out.println("Bruk: > java Oblig3B <tekstfil>");
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
	// Bruk data funnet til aa skrive oppsummering til en ny
	// skrivOppsummering(ord, antUnikeOrd, ant, antUnik);
	// Bruk data funnet til aa skrive antall forekomster av de hyppigste ordene
	forekomster(ord, antUnikeOrd);
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

    void forekomster(String[] ord, int[] antUnikeOrd){
	// Variabel for indeks til det hoeyeste tallet
	int indeks = 0;
	
	for(int i = 0; i < ord.length; i++) {
	    if(ord[i] == null) {
		break;
	    } else if(antUnikeOrd[i] > antUnikeOrd[indeks]) {
		// dersom et tall er hoeyere, legg indeksen inn i indeks variabelen
		indeks = i;
	    }
	}
	
	//Finne antall ord som trengs for 10 %
	int grense = antUnikeOrd[indeks]/10;
	
	for(int i = 0; i < ord.length; i++) {
	    if(ord[i] == null) {
		break;
	    } else if(antUnikeOrd[i] > grense) {
		// Skriv ut alle ord som gjentas mer enn 10 % av det mest gjentatte ordet, med formatering
		System.out.printf("Vanlige ord: %-15s (%d forekomster)%n", ord[i], antUnikeOrd[i]);
	    }
	}
    }// end forekomster
}// end Tekstanalyse