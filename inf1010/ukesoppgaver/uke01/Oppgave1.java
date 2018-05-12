import java.util.*;

class Oppgave1 {
	public static void main(String[] args) {
		Bibliotek b = new Bibliotek();
		Bok a = new Bok("Gud", "Bibelen");
		b.settInn(a.hentTittel(), a);
		a = new Bok("Keith Richards", "Life");
		b.settInn(a.hentTittel(), a);
		a = new Bok("Harald Rosenlow", "Yatzee");
		b.settInn(a.hentTittel(), a);

		System.out.println(b.hentBok("Life").hentForfatter() + " - Life");
		System.out.println(b.hentBok("Bibelen").hentForfatter() + " - Bibelen");
		System.out.println(b.hentBok("Yatzee").hentForfatter() + " - Yatzee");
	}
}

class Bok {
	private String forfatter;
	private String tittel;

	Bok(String forfatter, String tittel) {
		this.forfatter = forfatter;
		this.tittel = tittel;
	}

	public String hentForfatter() { return forfatter; }
	public String hentTittel() { return tittel; }
}

class Bibliotek {
	private HashMap<String, Bok> bokSamling = new HashMap<String, Bok>();

	public void settInn(String tittel, Bok ting) {
		bokSamling.put(tittel, ting);
	}

	public void taUt(String tittel) {
		bokSamling.remove(tittel);	
	}

	public Bok hentBok(String tittel) {
		return bokSamling.get(tittel);
	}
}