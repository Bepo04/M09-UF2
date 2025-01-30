import java.util.Random;

public class Assistent extends Thread {
    
    private Esdeveniment esdeveniment;
    private Random rnd;

    public Assistent(String nom, Esdeveniment esdeveniment) {
        super(nom);
        this.esdeveniment = esdeveniment;
        this.rnd = new Random();
    }

    @Override
    public void run() {
        while (true) {
            if (rnd.nextBoolean()) {
                esdeveniment.ferReserva(this);
            } else {
                esdeveniment.cancelaReserva(this);
            }
            try {
                sleep(rnd.nextInt(1001));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
