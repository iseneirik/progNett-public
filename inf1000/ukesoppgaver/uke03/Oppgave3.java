class Oppgave3
{
    public static void main(String[] args)
    {
	for(int i = 1; i <= 10; i++)
	{
	    System.out.print("Radius = " + i + " gir omkrets = ");
	    System.out.printf("%.2f\n", (i * 2 * 3.14));
	}
    }
}