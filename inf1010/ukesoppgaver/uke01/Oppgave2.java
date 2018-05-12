class Oppgave2  {
	public static void main(String[] args) {
		
		Film inception = new Film("Inception", "Christoffer Nolan", "Spenning");
		Film lotr = new Film("Lord of the Rings", "Peter Jackson", "Eventyr");
		
		Beholder<Film> filmEske = new Beholder<Film>();
		filmEske.settInn(inception);
		filmEske.settInn(lotr);
		
		Film lesInfo = filmEske.taUt();
		lesInfo.skrivInfo();
		lesInfo = filmEske.taUt();
		lesInfo.skrivInfo();
	}
}

class Film {
	String tittel;
	String regissor;
	String tema;

	Film(String tittel, String regissor, String tema) {
		this.tittel = tittel;
		this.regissor = regissor;
		this.tema = tema;
	}

	public void skrivInfo() {
		String info = String.format("Tittel: %s - Regissor: %s - Tema: %s", 
									 tittel, regissor, tema);
		System.out.println(info);
	}
}

class Beholder<T> {
	private T[] ting = (T[]) new Object[2];
	private int antall = 0;

	public void settInn(T t) {
		ting[antall++] = t;
	}

	public T taUt() {
		return ting[--antall];
	}
}

