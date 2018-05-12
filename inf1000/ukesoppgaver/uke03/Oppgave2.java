import java.util.*;

class Oppgave2
{
    public static void main(String[] args)
    {
	Scanner in = new Scanner(System.in);
	
	System.out.print("Oppgi verdien til x: ");
	double x = in.nextDouble();
	System.out.print("Oppgi verdien til y: ");
	double y = in.nextDouble();
	
	System.out.println("Produktet av " + x + " og " + y + " er ");
	System.out.printf("Med 2 desimaler: %.2f\n", (x * y));
	System.out.printf("Med 3 desimaler på 10 plasser: %10.3f\n", (x * y));
    }
}