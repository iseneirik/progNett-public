import java.util.*;

class Oppgave1 {
    public static void main(String[] args) {
	Scanner In = new Scanner(System.in);
	System.out.print("Regnestykke: ");
	int x = In.nextInt();
	char operand = In.next().charAt(0);
	int y = In.nextInt();

	System.out.print("Resultat: ");
	switch(operand) {
	    case '+': System.out.println(x+y); break;
	    case '-': System.out.println(x-y); break;
	    case '*': System.out.println(x*y); break;
	    case '/': System.out.println((double)x/y); break;
            default: System.out.println(" Kan ikke bruke '" + operand + "' i denne kalkulatoren!");
	}
    }
}