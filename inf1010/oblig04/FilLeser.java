import java.io.FileReader;
import java.io.LineNumberReader;
import java.lang.RuntimeException;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class FilLeser {

	public static final String dataDelimiter = ",";
	public static final String infoDelimiter = "#";
	public static final String personDelimiter = "# Personer";
	public static final String legeDelimiter = "# Leger";
	public static final String legemiddelDelimiter = "# Legemidler";
	public static final String reseptDelimiter = "# Resepter";
	public static final String eofDelimiter = " Slutt";

	private String filnavn;
	private LineNumberReader reader;

	public FilLeser(String filnavn) {
		this.filnavn = filnavn;
	}

	public void setFileToRead(String filnavn) {
		this.filnavn = filnavn;
	}


	public Tabell<Pasient> lesInnPasienter() {
		return lesInnPasienter(filnavn);
	}

	public Tabell<Pasient> lesInnPasienter(String filnavn) {
		ArrayList<String> linjer = finnAktuelleLinjer(personDelimiter, filnavn);
		Tabell<Pasient> pasienter = new Tabell<>(linjer.size());
		int hoyesteId = -1;

		for (String linje : linjer) {
			String[] pasientInfo = linje.split(dataDelimiter);
			int id = Integer.parseInt(pasientInfo[0].trim());
			String navn = pasientInfo[1].trim();
			char kjonn = pasientInfo[2].trim().charAt(0);
			Pasient pasient = new Pasient(id, navn, kjonn);
			if (id > hoyesteId) hoyesteId = id;
			if (!pasienter.settInn(pasient, id)) throw(new FilLeserException("Pasient kunne ikke settes inn i tabellen."));
		}
		Pasient.setPasientTeller(hoyesteId+1);
		return pasienter;
	}

	public SortertEnkelListe<Lege> lesInnLeger() {
		return lesInnLeger(filnavn);
	}

	public SortertEnkelListe<Lege> lesInnLeger(String filnavn) {
		ArrayList<String> linjer = finnAktuelleLinjer(legeDelimiter, filnavn);
		SortertEnkelListe<Lege> leger = new SortertEnkelListe();
		int hoyestePasientId = -1;

		for (String linje : linjer) {
			String[] legeInfo = linje.split(dataDelimiter);
			String navn = legeInfo[0].trim();
			boolean spesialist = (!(Integer.parseInt(legeInfo[1].trim()) == 0));
			int avtaleNr = Integer.parseInt(legeInfo[2].trim());
			Lege lege = null;
			if (avtaleNr > 0){
				leger.settInn(new AvtaleLege(navn, spesialist, avtaleNr));
			} else {
				leger.settInn(new Lege(navn, spesialist));
			}
		}

		return leger;
	}

	public Tabell<Legemiddel> lesInnLegemidler() {
		return lesInnLegemidler(filnavn);
	}

	public Tabell<Legemiddel> lesInnLegemidler(String filnavn) {
		ArrayList<String> linjer = finnAktuelleLinjer(legemiddelDelimiter, filnavn);
		Tabell<Legemiddel> legemidler = new Tabell<>(linjer.size());
		int hoyesteId = -1;

		for (String linje : linjer) {
			String[] info = linje.split(dataDelimiter);

			int id = Integer.parseInt(info[0].trim());
			String navn = info[1].trim();
			String form = info[2].trim();
			char type = info[3].trim().charAt(0);
			int pris = Integer.parseInt(info[4].trim());
			int mengde = Integer.parseInt(info[5].trim());
			int styrke = -1;
			if (info.length == 7) styrke = Integer.parseInt(info[6].trim());

			Legemiddel nyttMiddel = null;
			switch(form) {
				case Legemiddel.PILLE:
					nyttMiddel = new LegemiddelPille(id, navn, pris, type, styrke, mengde);
					break;
				case Legemiddel.LINIMENT:
					nyttMiddel = new LegemiddelLiniment(id, navn, pris, type, styrke, mengde);
					break;
				case Legemiddel.INJEKSJON:
					nyttMiddel = new LegemiddelInjeksjon(id, navn, pris, type, styrke, mengde);
					break;
			}

			if (id > hoyesteId) hoyesteId = id;
			if (!legemidler.settInn(nyttMiddel, id)) throw(new FilLeserException("Legemiddel kunne ikke settes inn i tabellen."));
		}

		return legemidler;
	}

	public EnkelReseptListe lesInnResepter(Legekontor kontor) {
		return lesInnResepter(filnavn, kontor);
	}

	public EnkelReseptListe lesInnResepter(String filnavn, Legekontor kontor) {
		ArrayList<String> linjer = finnAktuelleLinjer(reseptDelimiter, filnavn);
		EnkelReseptListe resepter = new EnkelReseptListe();
		int hoyesteId = -1;

		for (String linje : linjer) {
			String[] info = linje.split(dataDelimiter);

			int id = Integer.parseInt(info[0].trim());
			char type = info[1].trim().charAt(0);
			int persNr = Integer.parseInt(info[2].trim());
			String legeNavn = info[3].trim();
			int legemiddelNr = Integer.parseInt(info[4].trim());
			int reit = Integer.parseInt(info[5].trim());

			Resept resept = null;
			switch(type) {
				case 'h':
					resept = new HvitResept(kontor.legemiddelTabell.hent(legemiddelNr),
							kontor.legeListe.hent(legeNavn), persNr, reit);
					break;
				case 'b':
					resept = new BlaaResept(kontor.legemiddelTabell.hent(legemiddelNr),
							kontor.legeListe.hent(legeNavn), persNr, reit);
					break;
			}

			if (id > hoyesteId) hoyesteId = id;
			resepter.settInn(resept);
		}

		return resepter;
	}


	private ArrayList<String> finnAktuelleLinjer(String sokEtter) {
		return finnAktuelleLinjer(sokEtter, filnavn);
	}

	private ArrayList<String> finnAktuelleLinjer(String sokEtter, String filnavn) {
		String line = "";
		ArrayList<String> linjer = new ArrayList<>();
		try{
			reader = new LineNumberReader(new FileReader(filnavn));
			while (!line.startsWith(sokEtter)) {
				line = reader.readLine();
			}

			line = reader.readLine();
			while (line.length() > 0 && !line.startsWith(infoDelimiter)) {
				linjer.add(line.trim());
				line = reader.readLine();;
			}
		} catch(FileNotFoundException fnfe) {
			System.out.println("Kunne ikke finnne filen: " + filnavn);
		} catch(IOException ioe) {
			System.out.println("Det oppstod en feil under lesing av fil: " + filnavn);
		}

		return linjer;
	}
}

class FilLeserException extends RuntimeException {
	FilLeserException(String message) {
		System.out.println(message);
		printStackTrace();
	}
}