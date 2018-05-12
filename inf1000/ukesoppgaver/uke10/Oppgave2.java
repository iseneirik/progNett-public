import java.util.*;

class Konto {
    int nr; // kontonummer
    String navn; // eier
    int saldo;

    Konto(int nr, String navn, int saldo) {
	this.nr = nr;
	this.navn = navn;
	this.saldo = saldo;
    }

    void settInn(int innskudd) {
	saldo = saldo + innskudd;
    }
}

class Bank {
    HashMap<String,Konto> kontoer = new HashMap<String,Konto>();
    HashMap<String,Konto> kontoerNr = new HashMap<String,Konto>();

    //Konto[] kontoer = new Konto[1000];
    int antKontoer = 0;

    public static void main(String[] args) {
	Bank b = new Bank();
    }

    Bank() {
        åpneNyttKonto(530010, "Nils", 4000);
        åpneNyttKonto(720020, "Elin", 8000);
        åpneNyttKonto(910030, "Tina", 9000);

        //Konto k = finnKontoFraNavn("Elin");
        Konto k = kontoer.get("Elin");
        System.out.println("Elins kontonr: " + k.nr + ", saldo: " + k.saldo);

        k = kontoerNr.get(Integer.toString(530010));
        System.out.println("Kontonr. " + k.nr + " tilhører " + k.navn);
    }

    void åpneNyttKonto(int nr, String navn, int saldo) {
	Konto k = new Konto(nr, navn, saldo);
    kontoer.put(navn, k);
    kontoerNr.put(Integer.toString(nr), k);
	//kontoer[antKontoer] = k;
	antKontoer++;
    }

/*
    Konto finnKontoFraNavn(String navn) {
	for (int i = 0; i < antKontoer; i++) {
	    if (kontoer[i].navn.equals(navn)) {
		return kontoer[i];
	    }
	}
	return null;
    }
*/

/*
    Konto finnKontoFraNr(int kontonr) {
	for (Konto k : kontoer.values()) {
        if (k.nr == kontonr)
            return k;   
    }
*/

/*
    for (int i = 0; i < antKontoer; i++) {
	    if (kontoer[i].nr == kontonr) {
		return kontoer[i];
	    }
	}

	return null;
    }
*/
}