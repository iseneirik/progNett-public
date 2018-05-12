class Oppgave1{
    public static void main(String[] args){
	// Lage to variabler
	int x = 10;
	int y = 20;
	System.out.println("Variablene, x og y, har verdier: " + x + " og " + y);

	// Bytte om på verdiene så x -> y og y -> x
	int swap = x;
	x = y;
	y = swap;
	System.out.println("Variablene, x og y, har nå verdier: " + x + " og " + y);
    }
}

	
	