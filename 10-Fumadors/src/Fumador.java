import java.util.Random;

public class Fumador extends Thread {
    
    private Estanc estanc;
    private int id;
    private Tabac tabac;
    private Paper paper;
    private Llumi llumi;
    private int numFumades;
    private Random rnd;

    public Fumador(int id, Estanc estanc) {
        this.id = id;
        this.estanc = estanc;
        this.rnd = new Random();
        this.numFumades = 0;
    }

    @Override
    public void run() {
        while (numFumades <= 2) {
            compraTabac();
            compraPaper();
            compraLlumi();
            fuma();
        }
    }

    public void fuma() {
        if (tabac != null && llumi != null && paper != null) {
            tabac = null;
            llumi = null;
            paper = null;
            System.out.println("Fumador " + id + " fumant");
            numFumades++;
            try {
                sleep(rnd.nextInt(500, 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void compraTabac() {
        tabac = estanc.venTabac();
        if (tabac != null) {
            System.out.println("Fumador " + id + " comprant tabac");
        }
    }

    public void compraPaper() {
        paper = estanc.venPaper();
        if (paper != null) {
            System.out.println("Fumador " + id + " comprant paper");
        }
    }

    public void compraLlumi() {
        llumi = estanc.venLlumi();
        if (llumi != null) {
            System.out.println("Fumador " + id + " comprant llumi");
        }
    }

}
