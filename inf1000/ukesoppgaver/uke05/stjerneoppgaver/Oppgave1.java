import java.util.*;

class Oppgave1 {
    public static void main(String[] args) {
	Hjelpeklasse h = new Hjelpeklasse();

	h.skrivMeny();
	Scanner In = new Scanner(System.in);
	System.out.print("Skriv inn ditt valg: ");
	int valg = In.nextInt();

	switch(valg) {
	case 1: h.skrivFibonacci(); break;
	case 2: h.sjekkTall(); break;
	default: System.out.println("Feil valg!");
	}

    }
}

class Hjelpeklasse {
    
    void skrivMeny() {
	System.out.println("1. Skriv ut de 15 første tall i Fibonaccifølgen");
	System.out.println("2. Test om et tall hører til følgen");
    }

    void skrivFibonacci() {
	int a = 0;
	int b = 1;
	for(int i = 0; i < 15; i++){
	    System.out.print(a + " ");
	    int c = a;
	    a += b;
	    b = c;
	}
	System.out.println();
    }

    void sjekkTall() {
	Scanner In = new Scanner(System.in);
	System.out.print("Skriv inn ett tall: ");
	int x = In.nextInt();

	int a = 0;
	int b = 1;
	while(a <= x){
	    System.out.print(a + " ");
	    int c = a;
	    a += b;
	    b = c;
	}
	System.out.println();
	if(x == b)
	    System.out.println("Tallet finnes i følgen!");
	else
	    System.out.println("Tallet finnes ikke i følgen!");
	System.out.println();
    }
}