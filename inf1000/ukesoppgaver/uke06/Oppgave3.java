class Oppgave3 {
    public static void main(String[] args) {
        String testTekst = "ababaaaabccabcdbbda";
        MetoderOppgave3 m = new MetoderOppgave3();
        System.out.println(m.utenRepetisjon(testTekst));
        System.out.println(m.antallForskjellige(testTekst));
    }
}

class MetoderOppgave3 {

    boolean forekommer(char c, String tekst) {
        for (int i = 0; i < tekst.length(); i++) {
            if (tekst.charAt(i) == c) {
                return true;
            }
        }
        return false;
    }

    String utenRepetisjon(String tekst) {
        String nyTekst = "";
        for (int j = 0; j < tekst.length(); j++) {
            if (!forekommer(tekst.charAt(j), nyTekst)) {
                nyTekst += tekst.charAt(j);
            }
        }
        return nyTekst;
    }

    int antallForskjellige(String tekst) {
        return utenRepetisjon(tekst).length();
    }
}