import java.util.Random;

public class Filosof extends Thread {

    private long iniciGana;
    private long fiGana;
    private int gana;
    
    private Forquilla forquillaEsquerra;
    private Forquilla forquillaDreta;

    private Random rnd;

    public Filosof(int numComensal, String name, Forquilla dreta, Forquilla esquerra) {
        super(name);
        forquillaDreta = dreta;
        forquillaEsquerra = esquerra;
        gana = 0;
        rnd = new Random();
        iniciGana = 0;
    }

    public Forquilla getForquillaDreta() {
        return forquillaDreta;
    }

    public void setForquillaDreta(Forquilla forquillaDreta) {
        this.forquillaDreta = forquillaDreta;
    }

    public Forquilla getForquillaEsquerra() {
        return forquillaEsquerra;
    }

    public void setForquillaEsquerra(Forquilla forquillaEsquerra) {
        this.forquillaEsquerra = forquillaEsquerra;
    }

    public int getGana() {
        return gana;
    }

    public void setGana(int gana) {
        this.gana = gana;
    }

    public void agafarForquillaDreta() {
        forquillaDreta.agafar();
    }

    public void agafarForquillaEsquerra() {
        forquillaEsquerra.agafar();
    }

    public void agafarForquilles() {
        agafarForquillaEsquerra();
        agafarForquillaDreta();
        System.out.printf("%s té forquilles esq(%d) dreta(%d)%n", getName(), forquillaEsquerra.getNumero(), forquillaDreta.getNumero());
    }

    public void deixarForquilles() {
        forquillaDreta.deixar();
        forquillaEsquerra.deixar();
        System.out.printf("%s deixa les forquilles%n", getName());
    }


    public void menjar() {
        agafarForquilles();
        fiGana = System.currentTimeMillis();
        System.out.printf("%s menja amb gana %d%n", getName(), calcularGana());
        try {
            sleep(500, 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        deixarForquilles();
        System.out.printf("%s ha acabat de menjar%n", getName());
    }

    public void pensar() {
        resetGana();
        System.out.println(this.getName() + " pensant");
        try {
            sleep(rnd.nextLong(1000, 2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            menjar();
            pensar();
        }
    }

    public int calcularGana() {
        return (int) ((fiGana - iniciGana)/1000);
    }

    public void resetGana() {
        iniciGana = System.currentTimeMillis();
        gana = 0;
    }
}