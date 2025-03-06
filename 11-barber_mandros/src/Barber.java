import java.util.Random;

public class Barber extends Thread {

    private Random rnd;

    public Barber(String nom) {
        super(nom);
        rnd = new Random();
    }

    @Override
    public void run() {
        while (true) {
            Barberia barberia = Barberia.barberia;
            Client clientATallar = barberia.seguentClient();
            if (clientATallar != null) {
                clientATallar.tallarseElCabell();
                try {
                    sleep(900, rnd.nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                synchronized(barberia.getCondBarber()) {
                    try {
                        System.out.printf("Barber %s dormint.%n", getName());
                        barberia.getCondBarber().wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}