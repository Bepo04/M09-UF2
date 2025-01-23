import java.util.Random;

public class Soci extends Thread {
    
    private Compte compte;
    private float aportacio;
    private int esperaMax;
    private int maxAnys;
    private Random rnd;

    public Soci() {
        this.compte = Compte.getInstance();
        this.aportacio = 10f;
        this.esperaMax = 100;
        this.maxAnys = 100;
        this.rnd = new Random();
    }

    public Compte getCompte() {
        return compte;
    }

    @Override
    public void run() {
        for (int i = 0; i < maxAnys; i++) {
            for (int j = 1; j <= 12; j++) {
                if (j % 2 == 0) {
                    synchronized(compte) {
                        compte.setSaldo(compte.getSaldo() + aportacio);
                    }
                } else {
                    synchronized(compte) {
                        compte.setSaldo(compte.getSaldo() - aportacio);
                    }
                }
                try {
                    sleep(rnd.nextInt(esperaMax));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
