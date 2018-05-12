import java.util.*;
import java.io.*;

class Oppgave2 {
    final String DATAFIL = "akronymer.txt";
    HashMap<String,Akronym> akronymer = new HashMap<String,Akronym>();
    Scanner input;

    public static void main(String[] args) {
        Oppgave2 o = new Oppgave2();
        o.finnAkronym();
    }

    Oppgave2() {
        try {
            Scanner innfil = new Scanner(new File(DATAFIL));
            while (innfil.hasNextLine()) {
                String[] currLine = innfil.nextLine().split("\\s+");

                String akronym = currLine[0];
                String tolkning = "";
                for (int i = 1; i != currLine.length; i++) {
                    tolkning += currLine[i] + " ";       
                }   

                Akronym currAkr = new Akronym(akronym, tolkning);
                if(akronymer.containsKey(currAkr.akronym)) {
                    Akronym sub = akronymer.get(currAkr.akronym);
                    sub.nyTolkning(tolkning);
                } else {
                    akronymer.put(currAkr.akronym, currAkr);
                }
            }
            innfil.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void finnAkronym() {
        try {
            input = new Scanner(System.in);
            
            String sok = "";
            while(!sok.equals("-")){
                System.out.print("Skriv inn et akronym for sok ('-' for aa avslutte): ");
                sok = input.nextLine().toUpperCase();

                Akronym x = akronymer.get(sok);
                if(x == null) {
                    System.out.println(sok + " er ikke et akronym i denne databasen!");
                    nyAkronym(sok);
                } else {
                    x.skrivTolkninger();
                }
            }

            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void nyAkronym(String akronym) {
        System.out.print("Hva staar akronymet " + akronym + " for? ");
        String tolkning = input.nextLine();
        Akronym nyAkr = new Akronym(akronym, tolkning);
        akronymer.put(nyAkr.akronym, nyAkr);
    }
}

class Akronym {
    String[] tolkning = new String[10];
    int antTolkninger;
    String akronym;

    Akronym(String akronym, String tolkning) {
        this.akronym = akronym;
        this.tolkning[0] = tolkning;
        antTolkninger = 1;
    }

    void nyTolkning(String tolkning) {
        this.tolkning[antTolkninger] = tolkning;
        antTolkninger++;
    }

    void skrivTolkninger() {
        for (int i = 0; i != antTolkninger; i++) {
            System.out.println(tolkning[i]);
        }
    }
}