class Oppgave2{
    public static void main(String[] args){
	
	// Baade pose og sekk
	boolean pose = false;
	boolean sekk = true;
	
	if(pose && sekk)
	    System.out.println("Du får i både pose og sekk!");
	else if(!pose && sekk)
	    System.out.println("Du får i sekk, men ikke pose!");
	else if(pose && !sekk)
	    System.out.println("Du får i pose, men ikke i sekk!");
	else
	    System.out.println("Du får hverken i pose eller sekk!");
    }
}
	
	