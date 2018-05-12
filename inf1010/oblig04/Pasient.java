public class Pasient {
	static int teller = 0;
	public static final char KJONN_MANN = 'm';
	public static final char KJONN_KVINNE = 'k';

	private String navn;
	private char kjonn;
	private int pasientId;
	YngsteForstReseptListe reseptListe;

	Pasient(String navn, char kjonn) {
		this.navn = navn;
		this.kjonn = kjonn;
		pasientId = teller++;
		reseptListe = new YngsteForstReseptListe();
	} // end Constructor

	Pasient(int id, String navn, char kjonn) {
		pasientId = id;
		this.navn = navn;
		this.kjonn = kjonn;
		reseptListe = new YngsteForstReseptListe();
	} // end Constructor


	public static void setPasientTeller(int nyStartId) {
		if (nyStartId >= 0) teller = nyStartId;
	} // end setPasientTeller

	public String toString() {
		return String.format("%d, %s, %c", pasientId, navn, kjonn);
	} // end toString

	public String hentInfo() {
		return String.format("Navn: %s %nKjønn: %c %nID: %d %n", navn, kjonn, pasientId);
	} // end hentInfo

	public String getNavn() {
		return navn;
	}

	public int getId() {
		return pasientId;
	}

	public boolean erKvinne() {
		if (kjonn == KJONN_KVINNE) return true;
		return false;
	}
} // end Pasient