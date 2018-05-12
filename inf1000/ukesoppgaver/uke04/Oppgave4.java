import java.util.*;

class Oppgave4 {
    public static void main(String[] args) {

	Scanner In = new Scanner(System.in);
	char svar = 'j';

	while (svar == 'j') {
	    System.out.print("Skriv et navn: ");
	    String navn = In.nextLine();
	    
	    for(int i = 0; i < 100; i++)
		System.out.print(navn + " ");

	    System.out.println();
	    System.out.print("Gi nytt navn? (j/n): ");
	    svar = In.nextLine().charAt(0);
	}
    }
}
	