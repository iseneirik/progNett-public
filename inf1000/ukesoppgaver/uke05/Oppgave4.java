import java.util.Scanner;

class Oppgave4 {
    public static void main(String[] args) {
        TestMetoder tm = new TestMetoder();
        tm.start();
    }
}

class TestMetoder {
    Scanner tast = new Scanner(System.in); // Tilsvarer: In tast = new In();

    void start() {
	// Kaller en enkel metode:
	metode1();

	// Kaller en metode med én inn-parameter:
	skrivTredoblet(123);

	// Leser to tall fra tastatur, og overfører de til en metode
	// som finner og skriver ut det høyeste av de to tall:
	System.out.print("Skriv to tall (f.eks. 7 4): ");
	int tall1 = tast.nextInt(); // Tilsvarer: ... = tast.inInt();
	int tall2 = tast.nextInt(); // Tilsvarer: ... = tast.inInt();
	finnHøyesteAv2(tall1, tall2);

	// Kaller en metode som multipliserer de samme to tall lest
	// inn ovenfor, og returnerer resultatet hit.
	int resultat = multipliser(tall1, tall2);
	System.out.println("Resultat multiplisert: " + resultat);

	// Metode med array som inn-parameter:
	double[] verdier = { 0, -3, 5, 10, -20, -7.7, 1.2, -0.01 };
	int antNeg = finnAntallNegativeTall(verdier);
	System.out.println("Arrayen verdier[] har " + antNeg + " negative tall.");
	// <- Ta bort "//" i de to linjene over.
    }

    void metode1() {
	System.out.println("Dette er metode1");
    }

    void skrivTredoblet(int x) {
	int tredoblet = x * 3;
	System.out.println("Tredoblet resultat = " + tredoblet);
    }

    void finnHøyesteAv2(int a, int b) {
	int c = a;
	if(b > a)
	    c = b;

	System.out.println("Høyest av de to tall er: " + c);
    }

    int multipliser(int a, int b) {
	return a * b;
    }

    int finnAntallNegativeTall(double[] ia){
	int antNeg = 0;
	for(int i = 0; i < ia.length; i++)
	    if(ia[i] < 0)
		antNeg++;

	return antNeg;
    }
}
/* Utskrift:
Dette er metode1
Tredoblet resultat = 369
Skriv 2 tall (f.eks. 7 4): 7 4
Høyest av de to tall er: 7
Resultat multiplisert: 28
Arrayen verdier[] har 4 negative tall.
*/
