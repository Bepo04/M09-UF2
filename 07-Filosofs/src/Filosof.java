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

    public Forquilla getForquillaDreta() {
        return forquillaDreta;
    }

    public Forquilla getForquillaEsquerra() {
        return forquillaEsquerra;
    }

    public void menjar() {
        if (!forquillaEsquerra.getEnUs()) {

            forquillaEsquerra.setEnUs(true);
            System.out.println("Filòsof: " + this.getName() + " agafa la forquilla esquerra " + forquillaEsquerra.getNumero());

            if (!forquillaDreta.getEnUs()) {

                System.out.println("Filòsof: " + this.getName() + " agafa la forquilla dreta " + forquillaDreta.getNumero());
                forquillaDreta.setEnUs(true);
                gana = 0;
                System.out.println("Filòsof: " + this.getName() + " menja");
                forquillaDreta.setEnUs(false);
                forquillaEsquerra.setEnUs(false);
                System.out.println("Filòsof: " + this.getName() + " ha acabat de menjar");

            } else {
                forquillaEsquerra.setEnUs(false);
                System.out.printf("Filòsof: %s deixa l'esquerra(%d) i espera (dreta ocupada)%n", this.getName(), forquillaEsquerra.getNumero());
                gana++;
                System.out.printf("Filòsof: %s gana = %d%n", this.getName(), gana);
            }
        } else {
            gana++;
            System.out.printf("Filòsof: %s gana = %d%n", this.getName(), gana);            
        }
    }

    public void pensar() {
        System.out.println("Filòsof: " + this.getName() + " pensant");
        try {
            sleep(rnd.nextLong(1000, 2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true) {
            menjar();
            pensar();
        }
    }

}