public class RestaurantS {
    RestaurantS(String[] args) {
	   int antall = Integer.parseInt(args[0]);
       FellesBord bord = new FellesBord();
       Kokk  kokk = new Kokk(bord,antall);
	   kokk.start();
	   Servitor  servitor = new Servitor(bord,antall);
       servitor.start();
    }

    public static void main(String[] args) {
	       new RestaurantS(args);
    } 

    class FellesBord {  // En monitor
	private int antallPaBordet = 0;
        /* Tilstandsp�stand: 0 <= antallP�Bordet <= BORD_KAPASITET */
	private final int BORD_KAPASITET = 5;

	synchronized void settTallerken() {
	    while (antallPaBordet >= BORD_KAPASITET) {
		/* s� lenge det er  BORD_KAPASITET tallerkner
                               p� bordet er det ikke lov � sette p� flere. */
               try {  wait(); 
               } catch (InterruptedException e) {System.exit(1);}              
            }  // N� er antallPaBordet < BORD_KAPASITET
	    antallPaBordet++;	    
	    System.out.println("Antall paa bordet: " + antallPaBordet);
	    notify(); /* Si fra til den som tar tallerkener. */
	}

    synchronized void hentTallerken() {
        while (antallPaBordet == 0) {			
            /* S� lenge det ikke er noen talerkener p�
			      bordet er det ikke lov � ta en */
            try {  wait();
            } catch (InterruptedException e) { } 
        } // N� er antallP�Bordet > 0
	antallPaBordet --;      
        notify(); /* si fra til den som setter tallerkener p� bordet. */
    }
} // slutt class FellesBord;

class Kokk extends Thread {
    private FellesBord bord;
    private final int ANTALL;
    private int laget = 0;
    Kokk(FellesBord bord, int ant) {
		  this.bord = bord;
		  ANTALL = ant;
	  }

	  public void run() {
	      while(ANTALL != laget) {
              laget ++;
              System.out.println("Kokken lager tallerken nr: " + laget);
              bord.settTallerken();  // lag og lever tallerken             
              try { sleep((long) (500 * Math.random()));
              } catch (InterruptedException e) {}
          }
         // Kokken er ferdig
      }
  }

  class Servitor extends Thread {
      private FellesBord bord;
      private final int ANTALL; 
      private int servert = 0;
      Servitor(FellesBord bord, int ant) {
          this.bord = bord;
	  ANTALL = ant;
      }

      public void run() {
         while (ANTALL != servert)  {
	      bord.hentTallerken(); /* hent tallerken og server */
	      servert++;
              System.out.println("Kelner serverer nr:" +  servert);
              try { sleep((long) (1000 * Math.random()));
              } catch (InterruptedException e) {}
          }
         // Servitoren er ferdig
      }
  }
}

