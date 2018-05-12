import java.util.*;

class Oppgave2 {
    public static void main(String[] args){
	
	int[] oddetall = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
	
	Scanner In = new Scanner(System.in);
	System.out.print("Skriv inn et tall for sok: ");
	int sok = In.nextInt();

	for(int i = 0; i < oddetall.length; i++){
	    if(oddetall[i] == sok)
		System.out.println("Tallet " + sok + " finnes i arrayen");
	    if(oddetall[i] < sok)
		System.out.println(oddetall[i] + " er mindre enn det inntastede tallet!");
	}
    }
}