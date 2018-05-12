class Oppgave4
{
    public static void main(String[] args)
    {
	int i = 1;
	double o = (i * 2 * 3.14);
	while(o <= 1000)
	{
	    System.out.print("Radius = " + i + " gir omkrets = ");
	    System.out.printf("%.2f\n", o);
	    i++;
	    o = (i * 2 * 3.14);
	}
    }
}