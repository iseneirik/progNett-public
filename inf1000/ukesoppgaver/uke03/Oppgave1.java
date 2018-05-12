import java.util.*;

class Oppgave1
{
    public static void main(String[] args)
    {
	Scanner in = new Scanner(System.in);
	
	System.out.print("Oppgi verdien til x: ");
	float x = in.nextFloat();
	System.out.print("Oppgi verdien til y: ");
	float y = in.nextFloat();
	
	System.out.println("Produktet av " + x + " og " + y + " er " + (x * y));
    }
}