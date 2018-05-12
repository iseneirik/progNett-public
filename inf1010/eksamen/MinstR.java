public class MinstR {
    public static void main(String[ ] args) {
        new MinstR();
    }
    int [ ] tabell; 
    MinstMonitorR monitor;    
    public MinstR ( ) {     
       tabell = new int[6400000];
	 for (int in = 0; in< 6400000; in++) 
		tabell[in] = (int)Math.round(Math.random()* Integer.MAX_VALUE);
	 monitor = new MinstMonitorR();
	 for (int i = 0; i< 64; i++)       
            new MinstTradR(tabell,i*100000,((i+1)*100000)-1,monitor).start(); 
       monitor.vent();
       System.out.println("Minste verdi var: " + monitor.hentMinste());
    }
}

class MinstMonitorR {
    int minstTilNa = Integer.MAX_VALUE;
    int antallFerdigeSubtrader = 0;    
   synchronized void vent() {
       while (antallFerdigeSubtrader != 64) {
	     try {wait();          
                 System.out.println(antallFerdigeSubtrader + " ferdige subtrŒder ");
             }
             catch (InterruptedException e) {
		     System.out.println(" Uventet avbrudd ");  System.exit(0);
             }
       // antall ferdige subtrŒder er nŒ 64      
       }
  } 
  synchronized void giMinsteVerdi (int minVerdi) {
      antallFerdigeSubtrader ++;
      if(minstTilNa > minVerdi) minstTilNa = minVerdi;      
      if(antallFerdigeSubtrader == 64) notify();
      // eller hver gang, (men stort sett un¿dvendig):  notify();  
  }
  synchronized int hentMinste () {return minstTilNa;}  
}

class MinstTradR extends Thread {
    int [ ] tab; int startInd, endInd;
    MinstMonitorR mon;  
    MinstTradR(int [ ] tb, int st, int en, MinstMonitorR m) {
      tab = tb; startInd = st; endInd = en;
      mon = m; 
    }
    public void run(){
     int minVerdi = Integer.MAX_VALUE;     
     for ( int ind = startInd; ind <= endInd; ind++)
                   if(tab[ind] < minVerdi) minVerdi = tab[ind];  
        // signaler at denne tråden er ferdig med jobben:
        mon.giMinsteVerdi(minVerdi);
    } // slutt run 
} 



// try {sleep((int)Math.round(Math.random()* 10000));}
// catch (InterruptedException e) {
//	     System.out.println(" Noe grusomt skjedde");
//           System.exit(0);}
//  }
