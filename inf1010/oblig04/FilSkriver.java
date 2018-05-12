import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;

public class FilSkriver {

	public static final String CHARSET = "UTF-8";
	public static final String NL= System.lineSeparator();
	private static final String DEFAULT_SAVEFILE = "kontor-data.txt";

	private static final String PERSON_HEADING = "# Personer (nr, navn, kjønn)\n";
	private static final String LEGEMIDDEL_HEADING = "# Legemidler (nr, navn, form, type, pris, mengde [, styrke])\n";
	private static final String LEGE_HEADING = "# Leger (navn, spesialist, avtalenr / 0 hvis ingen avtale)\n";
	private static final String RESEPT_HEADING = "# Resepter (nr, hvit/blå, persNummer, legeNavn, legemiddelNummer, reit)\n";
	private static final String EOF = "# Slutt\n";

	private OutputStreamWriter writer;
	private String filnavn;
	private Legekontor kontor;

	public FilSkriver(Legekontor kontor) {
		this(DEFAULT_SAVEFILE, kontor);
	}

	public FilSkriver(String filnavn, Legekontor kontor) {
		this.filnavn = filnavn;
		this.kontor = kontor;
	}

	public void write() {
		write(filnavn);
	}

	public void write(String filnavn) {
		try{
			writer = new OutputStreamWriter(new FileOutputStream(new File(filnavn)), CHARSET);

			writer.write(PERSON_HEADING, 0, PERSON_HEADING.length());
			for (Pasient pasient : kontor.pasientTabell) {
				String output = pasient.toString();
				writer.write(output, 0, output.length());
				writer.write(NL, 0, NL.length());
			}

			writer.write(NL, 0, NL.length());

			writer.write(LEGEMIDDEL_HEADING, 0, LEGEMIDDEL_HEADING.length());
			for (Legemiddel middel : kontor.legemiddelTabell) {
				String output = middel.toString();
				writer.write(output, 0, output.length());
				writer.write(NL, 0, NL.length());
			}

			writer.write(NL, 0, NL.length());

			writer.write(LEGE_HEADING, 0, LEGE_HEADING.length());
			for (Lege lege : kontor.legeListe) {
				String output = lege.toString();
				writer.write(output, 0, output.length());
				writer.write(NL, 0, NL.length());
			}

			writer.write(NL, 0, NL.length());

			writer.write(RESEPT_HEADING, 0, RESEPT_HEADING.length());
			for (Resept resept : kontor.resepter) {
				String output = resept.toString();
				writer.write(output, 0, output.length());
				writer.write(NL, 0, NL.length());
			}

			writer.write(NL, 0, NL.length());

			writer.write(EOF, 0, EOF.length());

			writer.flush();
			writer.close();
		} catch(Exception e) {
			System.err.println("Error occured while writing to file: " + filnavn);
			e.printStackTrace();
		}
	}
}