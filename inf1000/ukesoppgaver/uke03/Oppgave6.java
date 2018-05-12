import java.util.*;

class Oppgave6{
    public static void main(String[] args){
	Scanner in = new Scanner(System.in);

	int[] tallArray = new int[5];
	int sum = 0;
	for(int i = 0; i < 5; i++){
	    System.out.print("Legg til ett tall: ");
	    tallArray[i] = in.nextInt();
	    sum += tallArray[i];
	}
	
	System.out.println("Summen av tallene du skrev er: " + sum);

	int minsteVerdi = tallArray[0];
	for(int i = 1; i < 5; i++){
	    if(tallArray[i] < minsteVerdi)
		minsteVerdi = tallArray[i];
	}
	System.out.println("Det minste tallet du skrev var: " + minsteVerdi);

	System.out.print("Av tallene du skrev er følgende lavere enn 10: ");
	for(int i = 0; i < 5; i++){
	    if(tallArray[i] < 10)
		System.out.print(tallArray[i] + " ");
	}
	System.out.println();

	for(int i = 0; i < 5; i++){
	    if(tallArray[i] == 5){
		System.out.println("Tallet 5 er blandt tallene du skrev!");
		break;
	    }
	}
    }
}
