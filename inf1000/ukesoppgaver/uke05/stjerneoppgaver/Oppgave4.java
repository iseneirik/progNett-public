import java.util.*;
import java.io.*;

class Oppgave4 {
    public static void main(String[] args) {
	try {
	    Scanner tekst = new Scanner(new File("dukkehjem.txt"));
	    Scanner In = new Scanner(System.in);

	    System.out.print("Skriv et ord: ");
	    String ord = In.nextLine().trim();

	    while(tekst.hasNextLine()) {
		String setning = tekst.nextLine();
		//System.out.println(setning);
		String[] delt = setning.split(" |\\,|\\.|\\?|\\!");
		for(int i = 0; i < delt.length; i++){
		    if(ord.equalsIgnoreCase(delt[i])){
			System.out.println(setning);
			break;
		    }
		}
	    }
	} catch(Exception e) {
	    e.printStackTrace();
	}
    }
}