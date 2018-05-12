class Oppgave2 {
    public static void main(String[] args) {
        String tekst = "Agnes i senga";
        for (int i = tekst.length()-1; i >= 0; i--) {
            System.out.print(tekst.toLowerCase().charAt(i));                
        }   
        System.out.println();
    }
}