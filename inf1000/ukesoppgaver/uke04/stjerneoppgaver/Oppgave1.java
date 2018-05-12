import java.util.*;

class Oppgave1 {
    public static void main(String[] args) {
	
	Scanner In = new Scanner(System.in);
	int[] tallArray = new int[5];
	
	for(int i = 0; i < tallArray.length; i++) {
	    System.out.print("Skriv inn tall " + (i+1) + "/" + tallArray.length + ": ");
	    tallArray[i] = In.nextInt();
	}

	System.out.print("Gjentatte tall: ");
	String gjentatteTall = "";

	for(int i = 0; i < tallArray.length; i++) {
	    for(int j = i+1; j < tallArray.length; j++) {
		if(tallArray[i] == tallArray[j]) {
		    if(!gjentatteTall.contains(" " + tallArray[i]))
			gjentatteTall += " " + tallArray[i];
		}
	    }
	}
	
	System.out.println(gjentatteTall);
    }
}