import java.util.*;

class Oppgave5 {
    public static void main(String[] args) {
	Oppgave6Metoder m = new Oppgave6Metoder();
	m.commandLoop();
    }
}

class Oppgave6Metoder {
    
    Scanner In = new Scanner(System.in);
    String navn;
    int antall;

    void commandLoop() {
	char svar = 'j';

	while(svar == 'j') {
	    System.out.print("Skriv et navn: ");
	    navn = In.nextLine();
	    
	    System.out.print("Antal reitisjoner av navn: ");
	    antall = In.nextInt();
	    In.nextLine();

	    utskrift(navn, antall);

	    System.out.print("Gi nytt navn? (j/n): ");
	    svar = In.nextLine().charAt(0);
	}
    }

    void utskrift(String navn, int antall) {

	for(int i = 0; i < antall; i++)
	    System.out.print(navn + " ");

	System.out.println();
    }
}