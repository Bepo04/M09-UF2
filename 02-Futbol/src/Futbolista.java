public class Futbolista extends Thread {

    private static int NUM_JUGADORS = 11;
    private static int NUM_TIRADES = 20;
    private static float PROBABILITAT = 0.5f;

    private int ngols;
    private int ntirades;

    public Futbolista(String nom) {
        super(nom); 
        this.ngols = 0;
        this.ntirades = 0;
    }

    public int getNgols() {
        return this.ngols;
    }

    public int getNtirades() {
        return this.ntirades;
    }

    @Override
    public void run() {
        for (int i = 0; i < NUM_TIRADES; i++) {
            ntirades++;
            if (Math.random() < PROBABILITAT) {
                ngols++;
            }
        }
    }

    public static void main(String[] args) {

        //Creem els jugadors i iniciem el seu fil
        Futbolista[] jugadors = new Futbolista[NUM_JUGADORS];
        System.out.println("Inici dels xuts --------------------");
        for (int i = 0; i < NUM_JUGADORS; i++) {
            jugadors[i] = new Futbolista("Jugador " + (i+1));
            jugadors[i].start();
        }

        //Esperem a que acabin tots els fils
        for (Futbolista jugador : jugadors) {
            try {
                jugador.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Fi dels xuts -----------------------");
        System.out.println("--- Estadístiques ------");
        for (Futbolista jugador : jugadors) {
            System.out.printf("%s -> %d gols\n", jugador.getName(), jugador.getNgols());
        }
    }
}