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

    public void agafarForquillaEsquerra() {
        synchronized (forquillaEsquerra) {
            while (forquillaEsquerra.getPropietari() != Forquilla.LLIURE) {
                try {
                    forquillaEsquerra.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            forquillaEsquerra.setPropietari(nunComensal);
        }
    }
    
    public boolean agafarForquillaDreta() {
        synchronized (forquillaDreta) {
            if (forquillaDreta.getPropietari() == Forquilla.LLIURE) {
                forquillaDreta.setPropietari(nunComensal);
                return true;
            } else {
                return false;
            }
        }
    }
    

    public boolean agafarForquilles() {
        agafarForquillaEsquerra();
        if (!agafarForquillaDreta()) {
            deixarForquillaEsquerra();
            return false;
        }
        return true;
    }
    

    public void deixarForquillaEsquerra() {
        synchronized (forquillaEsquerra) {
            if (forquillaEsquerra.getPropietari() == this.nunComensal) {
                forquillaEsquerra.setPropietari(Forquilla.LLIURE);
                forquillaEsquerra.notifyAll();
            }
        }
    }
    
    public void deixarForquillaDreta() {
        synchronized (forquillaDreta) {
            if (forquillaDreta.getPropietari() == this.nunComensal) {
                forquillaDreta.setPropietari(Forquilla.LLIURE);
                forquillaDreta.notifyAll();
            }
        }
    }

    public void deixarForquilles() {
        deixarForquillaEsquerra();
        deixarForquillaDreta();
    }

    public void menjar() {
        if (agafarForquilles()) {
            System.out.printf("Filòsof: %s menja%n", getName());
            gana = 0;
            try {
                sleep(rnd.nextLong(500, 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            deixarForquilles();
        } else {
            gana++;
            System.out.printf("Filòsof: %s gana=%d%n", getName(), gana);
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
        while (true) {
            menjar();
            pensar();
        }
    }
}