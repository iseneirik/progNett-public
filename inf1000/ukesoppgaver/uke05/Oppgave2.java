import java.util.*;

class Oppgave2{
    public static void main(String[] args){

	int[][] tabell = new int[3][4];
	for(int i = 0; i < tabell.length; i++)
	    for(int j = 0; j < tabell[i].length; j++)
		tabell[i][j] = (i+1) * (j+1);

	for(int i = 0; i < tabell.length; i++){
	    for(int y : tabell[i])
		System.out.print(y + "  ");
	    System.out.println();
	}

	System.out.println();

	for(int i = 0; i < tabell[0].length; i++){
	    int kolonnesum = 0;
	    for(int j = 0; j < tabell.length; j++)
		kolonnesum += tabell[j][i];
	    System.out.print(kolonnesum + "  ");
	}

	System.out.println("\n");

	Scanner In = new Scanner(System.in);
	System.out.print("Sok på et tall: ");
	int x = In.nextInt();
	for(int i = 0; i < tabell.length; i++)
	    for(int j = 0; j < tabell[i].length; j++){
		if(x == tabell[i][j])
		    System.out.println("Tallet finnes i posisjon: [" + i + "][" + j + "]");
	    }
    }
}