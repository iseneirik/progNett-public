class Oppgave3 {
	public static void main(String[] args) {
		 Skyskraper ss = new Skyskraper(360000.0, 20);
	}
}

class Bygning {
	Bygning(double bruttoAreal) {
		System.out.println("Ny byging er laget!");
		System.out.println("bygningen er " + bruttoAreal + " kvm. stort!");
	}

}

class Skyskraper extends Bygning {
	Skyskraper(double bruttoAreal, int antallEtasjer) {
		super(bruttoAreal);
		System.out.println("Ny Skyskraper er laget");
		System.out.println("Skyskraperen har " + antallEtasjer + " etasjer");
	}

}