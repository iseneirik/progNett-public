class Oppgave2{
    public static void main(String[] args){
	float inntekt = 100000f;
	float skatt = 0f;

	if(inntekt < 10000){
	    // Under 10 000 i inntekt betyr 10% skatt
	    skatt = inntekt * 0.1f;
	} else {
	    // Mer enn 10 000 i inntekt betyr 10% av 10 000 + 30% av resten
	    skatt = 10000 * 0.1f;
	    skatt += (inntekt-10000) * 0.3f;
	}

	System.out.println("Din inntekt er pa: " + inntekt);
	System.out.println("Du ma betale: " + skatt);
    }
}
	    