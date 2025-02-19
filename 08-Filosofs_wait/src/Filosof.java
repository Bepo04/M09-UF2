import java.util.Random;

public class Filosof extends Thread {

    private Forquilla forquillaDreta;
    private Forquilla forquillaEsquerra;
    private int gana;
    private int nunComensal;
    private Random rnd;

    public Filosof(int numComensal, String name, Forquilla dreta, Forquilla esquerra) {
        super(name);
        this.nunComensal = numComensal;
        forquillaDreta = dreta;
        forquillaEsquerra = esquerra;
        gana = 0;
        rnd = new Random();
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

    public int getNunComensal() {
        return nunComensal;
    }

    public void setNunComensal(int nunComensal) {
        this.nunComensal = nunComensal;
    }

    public boolean agafarForquillaDreta() {
        synchronized(forquillaDreta) {
            if (forquillaDreta.getPropietari() == Forquilla.LLIURE) {
                this.forquillaDreta.setPropietari(nunComensal);
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean agafarForquillaEsquerra() {
        synchronized (forquillaEsquerra) {
            if (forquillaEsquerra.getPropietari() == Forquilla.LLIURE) {
                this.forquillaEsquerra.setPropietari(nunComensal);
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean agafarForquilles() {
        synchronized (this) {
            while (!agafarForquillaEsquerra()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        if (!agafarForquillaDreta()) {
            deixarForquilles();
        }
        
        if (agafarForquillaEsquerra() && agafarForquillaDreta()) {
            return true;
        } else {
            gana++;
            return false;
        }
    }

    public void deixarForquillaEsquerra() {
        
    }

    public void deixarForquilles() {
        if (this.forquillaDreta.getPropietari() == this.nunComensal) {
            this.forquillaDreta.setPropietari(Forquilla.LLIURE);
        }
        if (this.forquillaEsquerra.getPropietari() == this.nunComensal) {
            this.forquillaEsquerra.setPropietari(Forquilla.LLIURE);
        }
        notifyAll();
    }

    public void menjar() {
        
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
        while (true) {
            menjar();
            pensar();
        }
    }
}