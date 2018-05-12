class Student {
    String navn;
}

class Gruppe {
    Student[] studenter = new Student[40]; // Array med Student-objekter
    int antStudenter; // Antall plasser i arrayen over som er i bruk

    public static void main(String[] args) {
        Gruppe g = new Gruppe();

        g.skrivAntall();
        System.out.println("Slutt");
    }

    Gruppe() {

        antStudenter = 0;

        studenter[antStudenter] = new Student();
        studenter[antStudenter].navn = "Trine";
        antStudenter++;

        Student s1 = new Student();
        s1.navn = "Martin";
        studenter[antStudenter++] = s1;

        if (studenter[1].navn.equals("Martin")) {
            System.out.println(true);
        }

        for (int i = 0; studenter[i] != null; i++) {
            Student s2 = studenter[i];
            System.out.println(s2.navn);
        }

        for (int i = 0; i < studenter.length; i++) {
            Student s3 = studenter[i];
            if(s3 != null)
                System.out.println(s3.navn);
        }

        // h) Hvorfor gir følgende linje i programkoden denne feilmeldingen:
        //    ArrayIndexOutOfBoundsException: 40
        //    studenter[40] = new Student();
        // Fordi 39 er det ytterste elementet

        // i) Hva vet vi om uttrykket merkert med "___" på neste linje hvis
        //    linjen gir feilmeldingen "ArrayIndexOutOfBoundsException: -1":
        //    s1 = studenter[ ___ ];
        // at ___ er tallet -1

        studenter[2].navn = "Eva";

        Student lars = new Student();
        lars.navn = "Lars";

        if (antStudenter == 0) {
            System.out.println("Ingen student registrert!");
        }

        // n) Når vi har rettet alle feilene i a) til m) ovenfor, så gir fortsatt
        //    if-setningen under NullPointerException.  Hvordan unngår vi det?
        boolean funnet = false;
        for (int i = 0; i < studenter.length; i++) {
            Student stud = studenter[i];
            if (stud != null && stud.navn.equals("Eva")) {
                funnet = true;
                System.out.println(stud.navn);
                break;
            }
        }

        if(!funnet){
            System.out.println("Navnet ble ikke funnet!");
        }

        Student ny = new Student();
        ny.navn = "Heidi";
        Student studPeker;
        studPeker = studenter[2];
        studPeker.navn = ny.navn;
        // Endret studenter[2].navn fra "Eva" til "Heidi" 
    }

    void skrivAntall() {
        System.out.println("Antall studenter: " + antStudenter);
    }
}