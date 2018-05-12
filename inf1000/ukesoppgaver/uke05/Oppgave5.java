import java.io.*;
import java.util.*;

class Oppgave5 {
    public static void main(String[] args) {
	try{
	    FileWriter fil = new FileWriter(new File("minfil.txt"));
	    fil.write('A' + "\n");
	    fil.write("Canis familiaris betyr hund\n");
	    fil.write(15 + "\n");
	    fil.write(String.format("%.2f", 3.1415) + "\n"); // Bruk to desimaler
	    fil.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}

	try{
	    Scanner fraFil = new Scanner(new File("minfil.txt"));
	    char a = fraFil.nextLine().charAt(0);
	    String b = fraFil.nextLine();
	    int c = fraFil.nextInt();
	    fraFil.nextLine();
	    double d = fraFil.nextDouble();

	    System.out.println(a + "\n" + b + "\n" + c + "\n" + d);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}