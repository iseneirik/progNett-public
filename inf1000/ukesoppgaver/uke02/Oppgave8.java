import easyIO.*;

class Oppgave8{
    public static void main(String[] args){
	
	In tall = new In();
	System.out.print("Hei, hvor gammel er du?: ");
	int alder = tall.inInt();
	
	if(alder < 12 || alder > 64)
	    System.out.println("Du får reise for halv pris!");
	else
	    System.out.println("Du må betale full pris!");
    }
}