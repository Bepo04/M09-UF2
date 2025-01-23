import java.util.Random;

public class Treballador extends Thread  {

    private float souAnualBrut;
    private int edatIniciTreball;
    private int edatFiTreball;
    private int edatActual;
    private float cobrat;
    private Random rnd;

    public Treballador(String nom, float souBrut, int edatInici, int edatFi) {
        super(nom);
        this.souAnualBrut = souBrut;
        this.edatIniciTreball = edatInici;
        this.edatFiTreball = edatFi;
        this.edatActual = 0;
        this.cobrat = 0.0f;
        this.rnd = new Random();
    }

    public void cobra() {
        this.cobrat += (souAnualBrut/12);
    }

    public void pagaImpostos() {
        this.cobrat -= (souAnualBrut/12f) * 0.24f;
    }

    public double getCobrat() {
        return cobrat;
    }

    public int getEdat() {
        return edatActual;
    }
    
    @Override
    public void run() {
        this.edatActual = this.edatIniciTreball;
        while (this.edatActual < edatFiTreball) {
            for (int i = 0; i < 12; i++) {
                try {
                    cobra();
                    sleep(rnd.nextInt(100));
                    pagaImpostos();
                    sleep(rnd.nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            edatActual++;
        }
    }

    @Override
    public String toString() {
        return String.format("%s -> edat: %d / total: %.2f%n", getName(), edatActual, cobrat);
    }
}