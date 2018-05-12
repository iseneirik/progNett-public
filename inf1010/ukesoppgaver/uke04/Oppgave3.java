import java.io.*;
import java.util.*;

class Oppgave3 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int x = in.nextInt();
		int y = in.nextInt();
		try {
			System.out.println(x + " / " + y + " = " + (x / y));
		} catch (ArithmeticException e) {
			System.out.println("Kan ikke dele paa 0!");
		}
	}
}

class DivisjonMedNull extends Exception {
	
}