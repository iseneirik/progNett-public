import java.util.*;
import java.io.*;

class Oblig5 {
    public static void main(String[] args) {
        Planlegger p = new Planlegger("trikkogtbaneutf8.txt");
        // p.printLinjer();
    }
}

class Planlegger { 
    // Hashmaps for aa holde stasjoner og linjer
    HashMap<String,Stasjon> stasjoner = new HashMap<String,Stasjon>();
    HashMap<Integer,Linje> linjer = new HashMap<Integer,Linje>();

    // Contructer for Planlegger
    Planlegger(String filnavn) {
        lesFraFil(filnavn);
        Stasjon fraStasjon = getStasjon("fra");
        Stasjon tilStasjon = getStasjon("til");
        finnReise(fraStasjon, tilStasjon);
    }

    void lesFraFil(String filnavn) {
        try {
            // Variabel for aa holde paa linjer som fylles med stasjoner
            Linje currLinje = null;
            Scanner innfil = new Scanner(new File(filnavn));
            while(innfil.hasNextLine()) {
                // Les hver linje av fila, legg inn i programmet
                String inndata = innfil.nextLine().toLowerCase();
                if (inndata.contains("*")) {
                    // Dersom navnet inneholder en '*', er det en linje, legg til i hashmap
                    currLinje = new Linje(Integer.parseInt(inndata.replaceAll("[\\D]", "")));
                    linjer.put(currLinje.linjeNr, currLinje);
                } else {
                    // Variabel for aa holde paa stasjonen som hentes
                    Stasjon currStasjon = null;
                    if (stasjoner.containsKey(inndata)) {
                        // Dersom stasjonen finnes, bruk den
                        currStasjon = stasjoner.get(inndata);
                    } else {
                        // Dersom stasjonen ikke finnes, legg den til
                        currStasjon = new Stasjon(inndata);
                        stasjoner.put(currStasjon.navn, currStasjon);
                    }
                    // Legg til stasjonen i linjen, og linjen i stasjonen
                    currLinje.stasjoner.add(currStasjon);
                    currStasjon.linjer.add(currLinje);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    Stasjon getStasjon(String s) {
        Scanner in = new Scanner(System.in);
        String stasjon = null;
        
        // Fortsett aa spoer bruker fram til stasjonen er gyldig
        while (true) {
            System.out.printf("Hvor vil du reise %s? ", s);
            stasjon = in.nextLine().toLowerCase();
            // break loopen naar en gyldig stasjon er funnet
            if (stasjoner.containsKey(stasjon)){break;}
            System.out.println("Det er ikke en gyldig stasjon!");
        }
        return stasjoner.get(stasjon);
    }

    void finnReise(Stasjon fra, Stasjon til) {
        // Variabler for aa holde paa korteste rute
        String kortesteRute = null;
        double kortesteTid = Double.POSITIVE_INFINITY;
        // Bool for aa vite om det finnes en direkte rute
        boolean direkte = false;
        for (Linje l : fra.linjer) {
            if (til.linjer.contains(l)) {
                // En direkte rute er funnet!
                direkte = true;
                Reise r = new Reise(l, fra, til);
                // Lag en string som beskriver reisen og skriv den ut
                String ruteString = String.format("Ta %s. Estimert tid: %.1f min %n", r.reiseString(), r.tid); 
                System.out.print(ruteString);
                // Sjekk om det er den korteste reisen
                if (r.tid < kortesteTid) {
                    kortesteRute = ruteString;
                    kortesteTid = r.tid;
                }
            }
        }

        // Dersom ingen direkte rute er funnet
        if (!direkte) {
            for (Linje l1 : fra.linjer) {
                for (Linje l2 : til.linjer) {
                    for (Stasjon s : l1.stasjoner) {
                        // Sjekk om l1 og l2 har felles stasjoner
                        if (l2.stasjoner.contains(s)) {
                            // Dersom de har felles, lag to reiser
                            // en fra start til fellesstasjon, en fra fellesstasjon til slutt
                            Reise r1 = new Reise(l1, fra, s);
                            Reise r2 = new Reise(l2, s, til);
                            double totaltid = (3.0 + r1.tid + r2.tid + ventetid(r2));
                            String ruteString = String.format("Ta %s og deretter %s. Estimert tid: %.1f min %n", r1.reiseString(), r2.leddString(), totaltid);
                            System.out.print(ruteString);
                            // Sjekk om det er den korteste reisen
                            if (totaltid < kortesteTid) {
                                kortesteRute = ruteString;
                                kortesteTid = totaltid;
                            }
                        }
                    }
                }
            }
        }

        System.out.print("\nKorteste reisevei:\n" + kortesteRute);
    }

    double ventetid(Reise r) {
        // Dersom det er overgang til trikk, er ventetid 5 min
        // Dersom det er overgang til t-bane, er ventetid 7.5 min
        return (r.type.equals("trikk") ? 5.0 : 7.5);
    }
}


class Linje {
    // Hver linje har oversikt over alle sine stasjoner
    ArrayList<Stasjon> stasjoner = new ArrayList<Stasjon>();
    int linjeNr;
    double tidMellomStopp;

    Linje(int linjeNr) {
        this.linjeNr = linjeNr;
        // Tid mellom stop er 1.8 min for t-bane og 1.4 for trikk
        tidMellomStopp = (linjeNr < 10 ? 1.8 : 1.4);
    }

    boolean inneholder(Stasjon st) {
        // Hvis linjen inneholder stasjonen, returner "true", ellers "false"
        return (stasjoner.contains(st) ? true : false);
    }

    String type() {
        // t-baner har linjeNr < 10, trikk har linjeNr > 10
        return (linjeNr < 10 ? "t-bane" : "trikk");
    }
}

class Stasjon {
    // Hver stasjon har oversikt over alle sine linjer
    ArrayList<Linje> linjer = new ArrayList<Linje>();
    String navn;

    Stasjon(String navn) {
        this.navn = navn;
    }    
}
    
class Overgang {
    // Jeg har tatt i bruk en losning som ikke krever klassen overgang
    // klassen Stasjon inneholder en arraylist med alle linjer som gaar gjennom 
}

class Reise {
    String type, fra, til, retning;
    int nr;
    double tid;

    Reise(Linje l, Stasjon fra, Stasjon til) {
        type = l.type();
        nr = l.linjeNr;
        this.fra = fra.navn;
        this.til = til.navn;
        retning = finnRetning(l, fra, til);
        tid = finnTid(l, fra, til);
    }

    String finnRetning(Linje l, Stasjon fra, Stasjon til) {
        // Returner navnet paa endestoppet basert paa hvilken retning man reiser
        if (l.stasjoner.indexOf(fra) < l.stasjoner.indexOf(til)) {
            return l.stasjoner.get(l.stasjoner.size()-1).navn;
        } else {
            return l.stasjoner.get(0).navn;
        }
    }

    double finnTid(Linje l, Stasjon fra, Stasjon til) {
        // antall stopp = absoluttverdien til indeksdifferansen av stasjoner paa en linje
        int antallStopp = Math.abs(l.stasjoner.indexOf(fra) - l.stasjoner.indexOf(til));
        return l.tidMellomStopp * antallStopp;
    }

    String reiseString() {
        return String.format("%s linje %d fra: %s til %s i retning %s", type, nr, fra, til, retning);
    }

    String leddString() {
        return String.format("%s linje %d retning %s til %s", type, nr, retning, til);
    }
}