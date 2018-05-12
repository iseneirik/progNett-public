class Lege implements Comparable<Lege>, Lik {
	protected String navn;
	protected boolean erSpesialist;
	EldsteForstReseptListe reseptListe;

	Lege(String navn, boolean spesialist) {
		this.navn = navn;
		erSpesialist = spesialist;
		reseptListe = new EldsteForstReseptListe();
	} // end Constructor

	public boolean spesialist() {
		return erSpesialist;
	} // end spesialist

	public boolean samme(String navn) {
		return navn.equalsIgnoreCase(this.navn);
	} // end samme

	public int compareTo(Lege lege) {
		return navn.compareToIgnoreCase(lege.navn);
	} // end compareTo

	public String toString() {
		return String.format("%s, %d, %d", navn, (erSpesialist ? 1 : 0), 0);
	} // end toString

	public String hentInfo() {
		return String.format("Navn: %s, %s%n", navn, (erSpesialist ? "spesialist" : "allmennlege"));
	} // end hentInfo

	public String getNavn() {
		return navn;
	}
} // end Lege

class AvtaleLege extends Lege implements Avtale {
	private static int teller = 1;

	private int avtaleNr;

	public static void setTeller(int nyStart) {
		teller = nyStart;
	}

	public AvtaleLege(String navn, boolean spesialist) {
		super(navn, spesialist);
		avtaleNr = teller++;
	} // end Constructor

	public AvtaleLege(String navn, boolean spesialist, int avtaleNr) {
		super(navn, spesialist);
		this.avtaleNr = avtaleNr;
	} // end Constructor

	public int hentAvtaleNr() {
		return avtaleNr;
	} // end hentAvtaleNr

	public String toString() {
		return String.format("%s, %d, %d", navn, (erSpesialist ? 1 : 0), avtaleNr);
	} // end toString


}