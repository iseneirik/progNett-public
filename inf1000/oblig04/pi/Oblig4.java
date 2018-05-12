import easyIO.*;

class Oblig4 {
    public static void main(String[] args) {
        if (args.length != 2) {
            // Dersom det ikke er lagt ved argumenter, forklar bruker
            System.out.println("Feil, bruk: java Oblig4 <antall siffer> <navn paa fil>");
        } else {
            // Lag et nytt objekt av Pi, send ved argumentene
            Pi p = new Pi(Integer.parseInt(args[0]), args[1]);
            p.printPi();
        }
    }
}// End Oblig4

class Pi {
    int[] pi;
    Out utfil;

    // Constructor, gir pi en passende storrelse
    Pi (int n, String fil) { 
        // Arrayen blir 1-2 plasser lengere i hver ende
        pi = new int[(n/4)+3];        
        utfil = new Out(fil);
    }
    
    void printPi () { 
        for (int i : pi) {
            String tall = addZero(i);
            utfil.out(tall + " ");
            System.out.print(tall + " ");
        }
        utfil.close();
    }
    
    void add() { /* legger verdien av en Arctan til i Pi */ }
    void sub() { /* trekker fra verdien av en Arktan fra Pi*/ }

    // Funksjon for aa passe paa at ikke 0-tall forsvinner om de er foerst
    String addZero(int i) {
        String tall = "" + i;
        while(tall.length() < 4) {
            tall = "0" + tall;
        }
        return tall;
    }
}// End Pi

class Arctan {
    Arctan () { 
        // Mottar tre parametere
        // n (antall sifre)
        // verdi (nevner i brok som tas arctan av)
        // mult (Faktoren foran arctan)
    }

    void add() { /* Addisjon i siffergrupper paa 4 */ }
    void sub() { /* Subtraksjon i siffergrupper paa 4 */ }
    void mul() { /* Multiplilasjon i siffergrupper paa 4 */ }
    void div() { /* Dividerer i siffergrupper paa 4 */ }
}// End Arctan