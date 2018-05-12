class Oppgave2 {
    public static void main(String[] args) {
	Oppgave2Metoder m = new Oppgave2Metoder();
	char[] bokstaver = {'A', 'R', 'N', 'E'};
	m.anagram(bokstaver);
    }
}

class Oppgave2Metoder {
    
    void anagram(char[] bokstaver) {
	for(int i = 0; i < bokstaver.length; i++) {
	    for(int j = 0; j < bokstaver.length; j++) {
		if(j != i) {
		    for(int k = 0; k < bokstaver.length; k++) {
			if(k != i && k != j) {
			    for(int l = 0; l < bokstaver.length; l++) {
				if(l != i && l != j && l != k)
				    System.out.println("" + bokstaver[i] + bokstaver[j] + bokstaver[k] + bokstaver[l]);
			    }
			}
		    }
		}
	    }
	}
    }
}
						       