import java.util.*;

class Oppgave8{
    public static void main(String[] args) {
	Scanner In = new Scanner(System.in);
	System.out.print("Skriv inn en tekst: ");
	String tekst = In.nextLine();
	System.out.println("Teksten er " + tekst.length() + " tegn lang!");
	tekst = tekst.toUpperCase();
	System.out.println(tekst);

	System.out.print("Skriv inn et ord: ");
	String a = In.nextLine();
	System.out.print("Skriv inn et til ord: ");
	String b = In.nextLine();
	if(a.equalsIgnoreCase(b))
	    System.out.println("Ordene er like!");
	else
	    System.out.println("Ordene er ikke like!");
    }
}
