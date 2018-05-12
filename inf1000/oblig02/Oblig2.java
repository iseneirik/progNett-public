import java.util.*;
import java.io.*;

class Oblig2{
    public static void main(String[] args) {
	
	Metoder m = new Metoder();
	System.out.println("Velkommen til fugletitterens dagbok!\n");
	m.commandLoop(); // Start programloopen

    } // end main()
} // end class Oblig2
 
class Metoder{   
    void commandLoop() {
	
	int menyValg = 0;

	// Programmet looper til brukeren velger avslutt
	while(menyValg != 4) {
	
	    menyValg = meny();
	    
	    // Sender brukeren til valget meny() sender tilbake
	    switch (menyValg) {
	        case 1: registrer(); break;
	        case 2: skrivFugletype(); break;
	        case 3: skrivSted(); break;
	        case 4: break;
	        default: System.out.println("Gi et tall mellom 1 og 4");
	    }
	}
    } // end commandLoop()
    
    int meny() {
		
	// Skriver ut brukerens valg
	System.out.println("\n* * * * * * * * * * * * * * * * * * * * * * * * * * * *");
	System.out.println("Hva vil du gjore?");
	System.out.println("1. Registrere en fugleobservasjon");
	System.out.println("2. Skriv ut alle observasjoner av en fugletype");
	System.out.println("3. Skriv ut alle observasjonene paa ett bestemt sted");
	System.out.println("4. Avslutt systemet");
	System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * *\n");

	// Henter brukerens valg
	Scanner In = new Scanner(System.in);
	System.out.print("Skriv inn ditt valg (1-4): ");
	int valg = In.nextInt();
	System.out.println();
	
	// Sender brukerens valg tilbake
	return valg;
    } // end meny()

    void registrer() {
		
	Scanner In = new Scanner(System.in);
		
	// Variabel for aa lagre registrering
	String nyFugl;
	
	// Legger til info om fuglen i nyFugl med ',' i mellom
	System.out.print("Fugletype:\t\t");
	nyFugl = In.next().trim();
	System.out.print("Kjonn (M/F/X):\t\t");
	nyFugl += "," + In.next().trim();
	System.out.print("Sted (Kommune):\t\t");
	nyFugl += "," + In.next().trim();
	System.out.print("Dato (eks: juli1993):\t");
	nyFugl += "," + In.next().trim();
	
	// Aapner en tekstfil og skriver til enden av filen
	try {
	    FileWriter fugler = new FileWriter(new File("fugler.txt"), true);
	    fugler.write(nyFugl + "\n");
	    fugler.close();	
	} catch	(IOException e) {
	    System.out.println("Problem ved aapning av fil");
	}
	
	System.out.println();
    
    } // end registrer()

    void skrivFugletype() {
	
	System.out.print("Fugletype for sok: ");
	Scanner In = new Scanner(System.in);
	// Variabel for aa lagre sok
	String typeFugl = In.next().trim();

	try{ 
	    Scanner fugleLog = new Scanner(new File("fugler.txt"));
	    
	    // Om loggen er tom, skriv ut beskjed
	    if(!fugleLog.hasNextLine())
		System.out.println("Beklager, det er ingen innforinger i loggen!\n");
	    else
		System.out.println("\n" + typeFugl.toUpperCase() + ":\n-------------------------");
	    	
	    // Skriv ut alle linjer som samsvarer med soket
	    while(fugleLog.hasNextLine()){
		String[] currLine = fugleLog.nextLine().split(",");
				
		if(currLine[0].toLowerCase().equals(typeFugl.toLowerCase()))
		    System.out.print(currLine[1] + "   " + currLine[2] + "\t" + currLine[3] + "\n");
	    }
	} catch (IOException e) {
	    System.out.println("Filen finnes ikke, eller er en annen plass!");
	}

    } // end skrivFugletype()

    
    // Samme funksjon som skrivFugletype(), med smaa forandringer
    void skrivSted() {

	System.out.print("Sted (kommune) for sok: ");
	Scanner In = new Scanner(System.in);
	String kommune = In.next().trim(); // Variabel for aa lagre sok
	
	try { 
	    Scanner fugleLog = new Scanner(new File("fugler.txt"));
	    
	    // Om loggen er tom, skriv ut beskjed
	    if(!fugleLog.hasNextLine())
		System.out.println("Beklager, det er ingen innforinger i loggen!\n");
	    else
		System.out.println("\n" + kommune + ":\n-------------------------");
	    
	    // Skriv ut alle linjer som samsvarer med soket
	    while(fugleLog.hasNextLine()){
		String[] currLine = fugleLog.nextLine().split(",");
				
		if(currLine[2].toLowerCase().equals(kommune.toLowerCase()))
		    System.out.print(currLine[0] + "\t" + currLine[1] + "   " + currLine[3] + "\n");
	    	}
	} catch (IOException e){
	    System.out.println("Filen finnes ikke, eller er en annen plass!");
	}
		
    } // end skrivSted()

} // end class Metoder

