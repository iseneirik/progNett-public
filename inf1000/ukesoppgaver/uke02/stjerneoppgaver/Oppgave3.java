class Oppgave3{
    public static void main(String[] args){
	int a = 123456;
	int b = 23453;
	int c = 457;

	if(a % 10 == b % 10){
	    if(b % 10 == c % 10){
		System.out.println("tallene, " + a + ", " + b + " og " + c + " har like siste siffer!");
	    } else {
		System.out.println("tallene, " + a + " og " + b + " har like siste siffer!");
	    }
	} 
	else if(a % 10 == c % 10)
	    System.out.println("tallene, " + a + " og " + c + " har like siste siffer!");
	else if(b % 10 == c % 10) 
	    System.out.println("tallene, " + b + " og " + c + " har like siste siffer!");
	else
	    System.out.println("Ingen av tallene har like siste siffer!");
    }
}
