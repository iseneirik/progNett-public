class Oppgave4 {
    public static void main(String[] args) {
        MetoderOppgave4 m = new MetoderOppgave4();
        String[] navn = {"Ole", "Dole", "Doffen", "Donald", "Dolly", "Langbein", "Pluto"};
        String[] kopi1 = m.arrayKopiering(navn);
        //String[] kopi2 = navn.System.arraycopy();
        m.sammenliknArray(navn, kopi1);
        //sammenliknArray(navn, kopi2);
    }
}

class MetoderOppgave4 {
    
    String[] arrayKopiering(String[] original) {
        String[] nyArray = new String[original.length];
        for (int i = 0; i < original.length; i++) {
            nyArray[i] = original[i];
        }
        return nyArray;
    }

    void sammenliknArray(String[] array1, String[] array2) {
        if (array1.length != array2.length) {
            System.out.println("Arrayene har ulik storrelse!");
        } else {
            for (int i = 0; i < array1.length; i++) {
                System.out.println(array1[i] + "  ---  " + array2[i]);
            }
        }
    }
}