import java.util.*;
import java.io.*;

class Oblig3C {
    public static void main(String[] args) {
    // Sjekk om bruker har lagt ved filnavn
        if (args.length < 1) {
            System.out.println("Bruk: > java Oblig3C <tekstfil>");
        } else {
            Tekstanalyse t = new Tekstanalyse(args[0]);
            //t.analyser(args[0]);
            
            // Bruk data funnet til aa skrive oppsummering til en ny
            t.skrivOppsummering();
            // Bruk data funnet til aa skrive antall forekomster av de hyppigste ordene
            t.forekomster();
            // Bruk data funnet til aa analysere antall ordpar
            t.ordPar();
        }
    }// end main
}// end Oblig3A

class Tekstanalyse {

    // Variabler for lagring av data i tekst
    // Alle funksjoner inni "Tekstanalyse" har tilgang til de
    String[] ord = new String[5000];
    int[] antUnikeOrd = new int[5000];
    int ant = 0;
    int antUnik = 0;

    Tekstanalyse(String filnavn) {
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

        while(tekst.hasNext()) {
            // Ordet som skjekkes
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
    
    }// end tekstOppsummering

    void skrivOppsummering() {
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


    void forekomster(){
    // Variabel for indeks til det hoeyeste tallet
        int indeks = 0;

        for(int i = 0; i < ord.length; i++) {
            if(ord[i] == null) {
                break;
            } else if(antUnikeOrd[i] > antUnikeOrd[indeks]) {
        // dersom et tall er hoeyere, legg indeksen inn i indekd variabelen
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

    void ordPar() {
        try {
        // Aapne fil aa lese ordpar fra
            Scanner parTekst = new Scanner(new File("alice.txt"));

        // Lage en array med stoerelse tilsvarende antall unike ord ^2
            int[][] antallPar = new int[antUnik][antUnik];


            String t1 = null;
            while(parTekst.hasNext()){
        // Hente neste ord
                String t2 = parTekst.next();
                if(t1 != null) {
            // Legge til en i indeksen som tilsvarer paret
                    antallPar[findIndeks(t1)][findIndeks(t2)]++;
                }
        // Sette t1 = t2 saa neste ord kan hentes inn i t2
                t1 = t2;
            }

        // Lukk parTekst
            parTekst.close();

        // Bruk data til aa finne antall par med alice foerst
            parAvOrd(findIndeks("alice"), antallPar);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }// end ordPar
    
    int findIndeks(String sokeOrd) {

        for(int i = 0; i < ord.length; i++) {
        // Dersom den finner ordet, send tilbake indeksen "i"
            if(ord[i].equalsIgnoreCase(sokeOrd)) {
                return i;
            }
        }
    // Dersom den ikke finner ordet i ordArr, gi tilbake en indeks som vil crashe programmet
        return -1;
    }

    void parAvOrd(int ordIndeks, int[][] antallPar) {
        System.out.println("\nOrd som kommer direkte etter ordet \"Alice\" i teksten:");
        for(int i = 0; i < antUnik; i++){
            if(antallPar[ordIndeks][i] > 0) {
                System.out.println(ord[i]);
            }
        }
    }

}// end Tekstanalyse