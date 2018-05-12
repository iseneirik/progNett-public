class Oppgave5 {
    public static void main(String[] args) {
        MetoderOppgave5 m = new MetoderOppgave5();
        String t1 = "fem";
        int t2 = 8;
        System.out.println(m.tallTilTekst(t2));
        System.out.println(m.tekstTilTall(t1));
    }
}

class MetoderOppgave5 {
    
    String[] tall = {"null", "en", "to", "tre", "fire", "fem", "seks", "sju", "otte", "ni"};

    String tallTilTekst(int t) {
        return tall[t];
    }

    int tekstTilTall(String t) {
        for (int i = 0; i < tall.length; i++) {
            if (tall[i].equalsIgnoreCase(t)) {
                return i;
            }
        }
        return -1;
    }
}