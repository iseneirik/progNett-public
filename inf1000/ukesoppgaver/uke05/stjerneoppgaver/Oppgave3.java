import java.util.*;
import java.io.*;

class Oppgave3 {
    public static void main(String[] args){
	try {
	    FileWriter nySang = new FileWriter(new File("nySang.txt"));
	    char[] vokaler = {'a', 'e', 'i', 'o', 'u', 'y', 'æ', 'ø', 'å'};

	    for(int i = 0; i < vokaler.length; i++) {

		Scanner sang = new Scanner(new File("sang.txt"));

		while(sang.hasNextLine()) {

		    String nyTekst = "";
		    String linje = sang.nextLine();

		    for(int j = 0; j < linje.length(); j++) {
			char c = linje.charAt(j);
			if(c=='a' || c=='e' || c=='i' || c=='o' || c=='u' || c=='y' || c=='æ' || c=='ø' || c == 'å')
			    c = vokaler[i];
			nyTekst += c;
		    }
		    nySang.write(nyTekst + "\n");
		}
		sang.close();
		nySang.write("\n");
	    }
	    nySang.close();
	} catch(Exception e) {
	    e.printStackTrace();
	}
    }
}