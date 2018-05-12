class Oblig3 {

    Oblig3() {
        ListeAvPersoner mineVenner = new ListeAvPersoner();

    	Person ane = new Person("Ane");
        mineVenner.settInnForst(ane);

        Person jonas = new Person("Jonas");
        mineVenner.settInnForst(jonas);

    	Person imran = new Person("Imran");
    	mineVenner.settInnSist(imran);

    	Person siri = new Person("Siri");
    	mineVenner.settInnEtter(imran, siri);

    	Person jan = new Person("Jan");
    	mineVenner.settInnSist(jan);

    	ane = mineVenner.finnPerson("Ane");
    	mineVenner.settInnEtter(ane, new Person("Mari"));
    	mineVenner.skrivAlle();

        // tester for sokPerson
        System.out.println("finnes Jonas? " + mineVenner.sokPerson(jonas));
        Person eirik = new Person("Eirik");
        System.out.println("finnes Eirik? " + mineVenner.sokPerson(eirik));
        mineVenner.settInnSist(eirik);
        System.out.println("finnes Eirik naa da? " + mineVenner.sokPerson(eirik));
    }
}


class Eks2014 {
    public static void main(String[] klargs) {
        new Oblig3();
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
        if (!sokPerson(nypers)) {   
            if (antall == 0) {
                sistePerson = nypers;
                nypers.neste = nypers;
            } else {
                nypers.neste = personliste.neste;
            }
            personliste.neste = nypers;
            antall++;
        }
    }
    
    public void settInnSist(Person inn){
        if(!sokPerson(inn)) {
            sistePerson.neste = inn;
            sistePerson = inn;
            antall++;
        }
    }

    public void settInnEtter(Person denne, Person inn) {
        if (!sokPerson(inn) && denne != inn) {
            if (denne == sistePerson) {
                settInnSist(inn);
            } else {   
                inn.neste = denne.neste;
                denne.neste = inn;
                antall++;
            }
        }
    }

    public Person finnPerson(String s) {
        Person p = personliste.neste;
        for (int i = antall; i>0; i--) {
            if (p.navn.equals(s)) return p;
            else p = p.neste;
        }
        return null;
    }

    public boolean sokPerson(Person sok) {
        Person p = personliste.neste;
        for (int i = antall; i > 0; i--) {
            if (p == sok) { return true; }
            p = p.neste;
        }
        return false;
    }
    
    public void skrivAlle() {
        Person p = personliste.neste;
        for (int i = antall; i > 0; i--) {
            p.skriv();
            p = p.neste;
        }
    }
}
