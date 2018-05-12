import java.util.*;
import java.io.*;

class Oppgave6{
    public static void main(String[] args){
	try{
	    Scanner vektFil = new Scanner(new File("elevvekt.txt"));
	    String[] navn = new String[27];
	    int[] vekt = new int[27];
	    for(int i = 0; i < navn.length; i++){
		navn[i] = vektFil.next();
		vekt[i] = vektFil.nextInt();
		vektFil.nextLine();
	    }

	    int maksVekt = 0;
	    int minVekt = 0;
	    for(int i = 0; i < vekt.length; i++){
		if(vekt[i] > vekt[maksVekt])
		    maksVekt = i;
		if(vekt[i] < vekt[minVekt])
		    minVekt = i;
	    }
	    System.out.println("Den tyngste personen er " + navn[maksVekt] + ", som veier: " + vekt[maksVekt]);
	    System.out.println("Den letteste personen er " + navn[minVekt] + ", som veier: " + vekt[minVekt]);

	    int total = 0;
	    for(int i : vekt)
		total += i;
	    double snittVekt = total/(double)vekt.length;

	    System.out.printf("Gjennomsnittsvekten er: %.2f kg!\n", snittVekt);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}