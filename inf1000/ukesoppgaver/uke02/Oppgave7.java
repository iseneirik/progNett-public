import easyIO.*;

class Oppgave7{
    public static void main(String[] args){
	In tekst = new In();
	System.out.print("Hva heter du?: ");
	String navn = tekst.inLine();
	System.out.println("Hei, " + navn + ". Velkommen til Ifi!");
    }
}
	