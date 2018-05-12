class Oppgave4 {
    public static void main(String[] args) {
        Kortstokk k = new Kortstokk();
        k.skrivUt();
    }
}

class Type {
    String navn;

    Type(String navn){
        this.navn = navn;
    }
}

class Kort {
    final String[] sort = {"Hjerter", "Ruter", "Spar", "Klover"};
    Type type;
    int verdi;    

    Kort(int verdi, int type){
        this.verdi = verdi;
        this.type = new Type(sort[type]);
    }
}

class Kortstokk {
    Kort[] kort = new Kort[52];

    Kortstokk(){
        for (int i = 0; i != 52; i++) {
            kort[i] = new Kort((i%13)+1, (i/13));
        }
    }

    void skrivUt() {
        for (Kort k : kort) {
            System.out.println(k.type.navn + k.verdi);
        }
    }
}
