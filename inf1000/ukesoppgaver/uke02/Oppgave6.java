import easyIO.*;

class Oppgave6{
    public static void main(String[] args){
	
	In tall = new In();
	System.out.print("Oppgi verdien til x: ");
	int x = tall.inInt();
	
	System.out.print("Oppgi verdien til y: ");
	int y = tall.inInt();

	System.out.println("Summen av x og y er " + (x + y) + ".");
    }
}