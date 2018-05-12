//Programmet vil ikke kompilere fordi y ikke faar en verdi for alle verdier av x

class Oppgave11{
    public static void main(String[] args) {
	int x = 2;
        int y;

        if (x > 0) {
            y = 8;
            String tekst = "if-testen slo til!";
        }
	System.out.println(y);
    }
}