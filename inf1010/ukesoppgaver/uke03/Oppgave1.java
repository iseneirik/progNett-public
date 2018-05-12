class Oppgave1 {

    Oppgave1() {
        ListeAvPersoner mineVenner = new ListeAvPersoner();

    	Person ane = new Person("Ane");
        mineVenner.settInnSist(ane);

    	Person jonas = new Person("Jonas");
    	mineVenner.settInnForst(jonas);

    	Person imran = new Person("Imran");
    	mineVenner.settInnSist(imran);

    	Person siri = new Person("Siri");
    	mineVenner.settInnEtter(ane, siri);

    	Person jan = new Person("Jan");
    	mineVenner.settInnEtter(ane, jan);
            
    	ane = mineVenner.finnPerson("Ane");
    	mineVenner.settInnEtter(ane, new Person("Mari"));
    	mineVenner.skrivAlle();
    }
}


class Eks2014 {
    public static void main(String[] klargs) {
        new Oppgave1();
    }
}


class Person {
    String navn;
    Person neste;

    Person(String n) {
	   navn = n;
    }

    public void skriv(){
        System.out.println(navn);
    }
}


class ListeAvPersoner {
    private Person personliste, sistePerson;
    private int antall;
    
    ListeAvPersoner(){
        Person lh = new Person("LISTEHODE!!");
    	personliste = lh;
        sistePerson = lh;
        antall = 0;
    }

    /* 
       Invariante tilstandspÃ¥stander
       (skal gjelde fÃ¸r og etter alle metodekall):
       personliste peker pÃ¥ listehodet
       fÃ¸rste person i lista er personliste.neste
       Hvis lista er tom er antall lik 0 og 
       personliste og sistePerson peker pÃ¥
       listehodet og personliste.neste er null.
    */
       
    public void settInnForst(Person nypers){
        nypers.neste = personliste.neste;
        personliste.neste = nypers;
        antall++;
    }
    
    public void settInnSist(Person inn){
        sistePerson.neste = inn;
        sistePerson = inn;
        antall++;
    }

    public void settInnEtter(Person denne, Person inn) {
        inn.neste = denne.neste;
        denne.neste = inn;
        antall++;
    }

    public Person finnPerson(String s) {
        Person p = personliste.neste;
        for (int i = antall; i>0; i--) {
            if (p.navn.equals(s)) return p;
            else p = p.neste;
        }
        return null;
    }

    public void skrivAlle() {
        Person p = personliste.neste;
        for (int i = antall; i>0; i--) {
            p.skriv();
            p = p.neste;
        }
    }
}
