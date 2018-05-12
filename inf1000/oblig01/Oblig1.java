class Oblig1 {
    public static void main(String[] args) {
	// Antall regndager(rd) og millimeter nedborsmengde(nm) i mai, juni og juli
	int rdMai = 22;
	int nmMai = 89;
	int rdJuni = 18;
	int nmJuni = 127;
	int rdJuli = 8;
	int nmJuli = 19;

	// Total antall regndager og total nedborsmengde
	int rdTot = rdMai + rdJuni + rdJuli;
	int nmTot = nmMai + nmJuni + nmJuli;

	// Gjennomsnittlig(avg) nedborsmengde de dagene det regnet
	double nmAvg = (double)nmTot/(double)rdTot;

	// Julis prosentandel av normalnedbor på 81mm
	double juliProsent = (nmJuli/81.0)*100;

	//Utskrift av informasjon
	System.out.println("Total nedborsmengde sommeren 2013:             " + nmTot + "mm");
	System.out.println("Gjennomsnittelig nedborsmengde per nedborsdag: " + Math.round(nmAvg*100)/100.0 + "mm");
	System.out.println("Prosentandel av normalnedbor for juli 2013:    " + Math.round(juliProsent*100)/100.0 + "%");
    }
}