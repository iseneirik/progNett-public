class Oppgave1{
    public static void main(String[] args){

	for(int i = 10; i >= 0; i--)
	    System.out.print(" ..." + i);

	System.out.println("\n");

	for(int i = 1; i <= 10; i++)
	    System.out.print((int)Math.pow(2, i) + "  ");

	System.out.println("\n");
	
	for(int i = 1; i <= 3; i++) {
	    for(int j = 1; j <= 4; j++)
		System.out.print((i * j) + "  ");
	    System.out.println();
	}
    }
}