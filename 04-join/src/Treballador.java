import java.util.Random;

public class Treballador extends Thread  {

    private double souAnualBrut;
    private int edatIniciTreball;
    private int edatFiTreball;
    private int edatActual;
    private double cobrat;
    private Random rnd;

    public Treballador(String nom, double souBrut, int edatInici, int edatFi) {
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
        double aPagar = (souAnualBrut/12) * 0.24;
    }

}