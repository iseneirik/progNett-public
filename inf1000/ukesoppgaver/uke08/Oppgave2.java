class Oppgave2 {
    public static void main(String[] args) {    
        int[] grupper = {2, 3, 4, 2, 4, 1, 3};
        Skole s = new Skole(grupper);
        s.skrivUt();
    }
}

class Skole {
    final int ANTALLTRINN = 7;
    KlasseTrinn[] klasseTrinn = new KlasseTrinn[ANTALLTRINN];

    Skole(int[] antGrupper){
        for (int i = 0; i != klasseTrinn.length; i++) {
            klasseTrinn[i] = new KlasseTrinn(antGrupper[i], i+1);
        }
    }

    void skrivUt() {
        for (KlasseTrinn k : klasseTrinn) {
            k.skrivUt();
        }
    }
}

class KlasseTrinn {
    final int ANTALLGRUPPER = 4;
    Gruppe[] gruppe = new Gruppe[ANTALLGRUPPER];

    KlasseTrinn(int antGrupper, int trinn) {
        for (int i = 0; i != antGrupper; i++) {
            gruppe[i] = new Gruppe(trinn, (char)(i+65));
        }
    }

    void skrivUt() {
        for (Gruppe g : gruppe) {
            g.skrivUt();
        }
    }
}

class Gruppe {
    String navn;

    Gruppe(int trinn, char gruppe){
        navn = "" + trinn + gruppe;
    }

    void skrivUt() {
        System.out.println(navn);
    }
}