class Oppgave1{
    public static void main(String[] args){
	int[] oddetall = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
	int sum = 0;
	for(int i = 0; i < oddetall.length; i++){
	    sum += oddetall[i];
	    System.out.println("Indeks: " + i + "\tTall: " + oddetall[i]);
	}
	System.out.println("\nSummen av tallene i arrayen er: " + sum);
    }
}