import java.util.*;

class Oppgave1 {
    public static void main(String[] args) {
        HashMap<String,Dyr> dyreHash = new HashMap<String,Dyr>();
        Dyr dyr = new Dyr("Hest", "0001");
        dyreHash.put(dyr.getIDnr(), dyr);
        dyr = new Dyr("Ku", "0002");
        dyreHash.put(dyr.getIDnr(), dyr);
        dyr = new Dyr("Geit", "0003");
        dyreHash.put(dyr.getIDnr(), dyr);
    }
}

class Dyr {
    String navn;
    String idnr;

    Dyr(String navn, String idnr) {
        this.navn = navn;
        this.idnr = idnr;
    }

    String getIDnr() {
        return idnr;
    }
}