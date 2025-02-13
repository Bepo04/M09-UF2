import java.util.Random;

public class Filosof extends Thread {

    private Forquilla forquillaDreta;
    private Forquilla forquillaEsquerra;
    private int gana;
    private Random rnd;

    public Filosof(String name, Forquilla dreta, Forquilla esquerra) {
        super(name);
        forquillaDreta = dreta;
        forquillaEsquerra = esquerra;
        gana = 0;
        rnd = new Random();
    }
}