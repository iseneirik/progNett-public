import java.util.concurrent.*;

public class MinstB {
    public static void main(String[ ] args) {
        new MinstB();
    }
    int [ ] tabell; 
    final int antallTrader = 64;
    CountDownLatch bariere = new CountDownLatch(antallTrader);
    MinstMonitorB mon = new MinstMonitorB ();
    public MinstB ( ) {     
       tabell = new int[6400000];
	 for (int in = 0; in< 6400000; in++) 
		  tabell[in] = (int)Math.round(Math.random()* Integer.MAX_VALUE);
	 for (int i = 0; i< antallTrader; i++)
       // Lag og start antallTrader tråder        
        new MinstTradB(tabell,i*100000,((i+1)*100000)-1,mon, bariere, i+1).start(); 
        //vent på at eller trådene er ferdig:
	    try {bariere.await();
             System.out.println("Minste verdi var: " + mon.hentMinste());
	    }
	    catch (InterruptedException ex){ }
    }
}

class MinstMonitorB {
    int minstTilNa = Integer.MAX_VALUE;
    synchronized void giMinsteVerdi (int minVerdi) {
        if(minstTilNa > minVerdi) minstTilNa = minVerdi;      
    }
  synchronized int hentMinste () {return minstTilNa;}
}

class MinstTradB extends Thread {
    int [ ] tab; int startInd, endInd;
    CountDownLatch bariere;
    MinstMonitorB mon;  int num;
    MinstTradB(int [ ] tb, int st, int en, MinstMonitorB mon, CountDownLatch bariere,
       int nr) {
      tab = tb; startInd = st; endInd = en; num= nr;
      this.mon = mon;  this. bariere = bariere;
    }

   public void run(){
     int minVerdi = Integer.MAX_VALUE;     
     for ( int ind = startInd; ind <= endInd; ind++)
                   if(tab[ind] < minVerdi) minVerdi = tab[ind];
        // signaler at denne tråden er ferdig med jobben:
        mon.giMinsteVerdi(minVerdi);
	System.out.println("ferdig " + num);
        bariere.countDown();
   }  
} 

