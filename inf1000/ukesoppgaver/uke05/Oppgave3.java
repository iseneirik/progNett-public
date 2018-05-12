class Oppgave3{
    public static void main(String[] args){
	MatteMet m = new MatteMet();

	for(int i = 1; i <= 6; i++)
	    for(int j = 1; j <= 6; j++){
		System.out.println("Kat 1: " + (double)i); 
		System.out.println("Kat 2: " + (double)j);
		System.out.println("Hypotenus: " + m.finnHypotenus((double)i, (double)j));
	    }
    }
}

class MatteMet{
    double finnHypotenus(double a, double b) {
	return Math.sqrt(Math.pow(a,2) + Math.pow(b,2));
    }
}