class Oppgave4 {
    public static void main(String[] args) {
        
        Person p1 = new Person("Eirik", 20, "Oslo"); 
        Person p2 = new Person("Hakon", 13, "Oslo");
    }
}

class Mobil {
    String merke;
    String eier;
    int tlf;

    Mobil(String m, String e, int t) {
        merke = m;
        eier = e;
        tlf = t;
    }
}

class Person {
    String navn;
    int alder;
    String bosted;
    Mobil m;

    Person(String n, int a, String b) {
        navn = n;
        alder = a;
        bosted = b;

        if (alder >= 14) {
            m = new Mobil("Nokia", navn, 12345678);
        }
    }
}
