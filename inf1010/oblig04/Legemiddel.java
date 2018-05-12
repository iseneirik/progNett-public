abstract class Legemiddel {
	// Statiske variabler for de tre typene
	public static final String PILLE = "pille";
	public static final String LINIMENT = "liniment";
	public static final String INJEKSJON = "injeksjon";

	// teller for aa skape ID for legemidler
	private static int teller = 0;

	// Info for hvert legemiddel
	protected String navn;
	protected int pris;
	protected int id;
	protected char type;
	protected int styrke;

	Legemiddel(String navn, int pris, char type, int styrke) {
		this.navn = navn;
		this.pris = pris;
		this.type = type;
		this.styrke = styrke;
		id = teller++;

	} // end Constructor

	Legemiddel(int id, String navn, int pris, char type, int styrke) {
		this(navn, pris, type, styrke);
		this.id = id;
		teller = id+1;
	} // end Constructor

	public String retType() {
		if (isTypeA()) { return "Type:   Narkotisk\n"; }
		if (isTypeB()) { return "Type:   Vanedannende\n"; }
		return "";
	} // end retType

	public boolean isTypeA() {
		if (type == 'a') return true;
		return false;
	} // end isTypeA

	public boolean isTypeB() {
		if (type == 'b') return true;
		return false;
	} // end isTypeB

	public boolean isTypeC() {
		if (type == 'c') return true;
		return false;
	} // end isTypeC

	public String hentInfo() {
		return String.format("Navn:   %s%nPris:   %d,-%nForm:   %s%nMengde: %s%n%sStyrke: %d%nID:     %d%n", navn, pris, form(), mengde(), retType(), styrke, id);
	}// end hentInfo

	public int getId() {
		return id;
	}

	public String getNavn() {
		return navn;
	}

	public int getPris() {
		return pris;
	}

	public abstract String form();
	public abstract String mengde();
} // end Legemiddel

class LegemiddelPille extends Legemiddel implements Piller {
	private int antallPiller;

	public LegemiddelPille(String navn, int pris, char type, int styrke, int mengde) {
		super(navn, pris, type, styrke);
		antallPiller = mengde;
	} // end Constructor

	public LegemiddelPille(int id, String navn, int pris, char type, int styrke, int mengde) {
		super(id, navn, pris, type, styrke);
		antallPiller = mengde;
	} // end Constructor

	public int antallPiller() {
		return antallPiller;
	} // end antallPiller

	public String form() {
		return PILLE;
	} // end form

	public String mengde() {
		return String.format("%d piller", antallPiller);
	}

	public String toString() {
		String s = String.format("%d, %s, %s, %c, %d, %d", id, navn, form(), type, pris, antallPiller); 
		return s;
	}
} // end LegemiddelPille

class LegemiddelLiniment extends Legemiddel implements Liniment{
	private int volum;

	public LegemiddelLiniment(String navn, int pris, char type, int styrke, int mengde) {
		super(navn, pris, type, styrke);
		volum = mengde;
	} // end Constructor

	public LegemiddelLiniment(int id, String navn, int pris, char type, int styrke, int mengde) {
		super(id, navn, pris, type, styrke);
		volum = mengde;
	} // end Constructor

	public int volum() {
		return volum;
	} // end volum

	public String form() {
		return LINIMENT;
	} // end form

	public String mengde() {
		return String.format("%d cm3", volum);
	}

	public String toString() {
		String s = String.format("%d, %s, %s, %c, %d, %d", id, navn, form(), type, pris, volum); 
		return s;
	}
}// end LegemiddelLiniment

class LegemiddelInjeksjon extends Legemiddel implements Injeksjon {
	private int dosering;

	public LegemiddelInjeksjon(String navn, int pris, char type, int styrke, int dose) {
		super(navn, pris, type, styrke);
		dosering = dose;
	} // end Constructor

	public LegemiddelInjeksjon(int id, String navn, int pris, char type, int styrke, int dose) {
		super(id, navn, pris, type, styrke);
		dosering = dose;
	} // end Constructor

	public int dosering() {
		return dosering;
	} // end dosering

	public String form() {
		return INJEKSJON;
	} // end form

	public String mengde() {
		return String.format("%d ml", dosering);
	}

	public String toString() {
		String s = String.format("%d, %s, %s, %c, %d, %d", id, navn, form(), type, pris, dosering); 
		return s;
	}
} // end LegemiddelInjeksjon

interface Piller {
	int antallPiller();

} // end Piller

interface Liniment {
	int volum();

} // end Liniment

interface Injeksjon {
	int dosering();

} // end Injeksjon
