abstract class Resept {
	// teller for aa gi resepter en id
	static int teller = 0;

	// informasjon som trengs for hver resept
	protected Legemiddel middel;
	protected Lege lege;
	protected int pasientId;
	protected int reit;
	protected int reseptId;

	Resept(Legemiddel middel, Lege lege, int pasientId, int reit) {
		this.middel = middel;
		this.lege = lege;
		this.pasientId = pasientId;
		this.reit = reit;

		reseptId = teller++;
	} // end Constructor

	public boolean erGyldig() {
		return (reit > 0);
	} // end erGyldig

	public void minusReit() {
		reit--;
	}

	public int getReseptId() {
		return reseptId;
	} // end getReseptId

	public int getPasientId() {
		return pasientId;
	} // end getPasientId

	public Lege getLege() {
		return lege;
	} // end getLege

	public Legemiddel getMiddel() {
		return middel;
	}

	public int getPris() {
		return middel.getPris();
	}

	// Returnerer en string med info man kan skrive til skjerm
	public String hentInfo() {
		return String.format("Legemiddel: %s%nReit: %d%nLege: %s%nPasientID: %d%nReseptID: %d%n", 
							 middel.getNavn(), reit, lege.getNavn(), pasientId, reseptId);
	} // end hentInfo

} // end Resept

class BlaaResept extends Resept {
	BlaaResept(Legemiddel middel, Lege lege, int persNr, int reit) {
		super(middel, lege, persNr, reit);
	} // end Constructor

	public String toString() {
		return String.format("%d, %c, %d, %s, %d, %d", reseptId, 'b', pasientId, lege.getNavn(), middel.getId(), reit);
	}

	public int getPris() {
		return 0;
	}

} // end BlaaResept

class HvitResept extends Resept {
	HvitResept(Legemiddel middel, Lege lege, int persNr, int reit) {
		super(middel, lege, persNr, reit);
	}// end Constructor

	public String toString() {
		return String.format("%d, %c, %d, %s, %d, %d", reseptId, 'h', pasientId, lege.getNavn(), middel.getId(), reit);
	}
} // end HvitResept