import java.util.ArrayList;
import java.util.Random;

public class Estanc extends Thread {

    private ArrayList<Tabac> tabakitosKlk;
    private ArrayList<Llumi> flamisells;
    private ArrayList<Paper> papelillo;
    private boolean estaTancat;
    private Random rnd;

    public Estanc() {
        this.tabakitosKlk = new ArrayList<Tabac>();
        this.flamisells = new ArrayList<Llumi>();
        this.papelillo = new ArrayList<Paper>();
        rnd = new Random();
        this.estaTancat = false;
    }

    public synchronized Tabac venTabac() {
        while (!tabakitosKlk.isEmpty() && !estaTancat) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return tabakitosKlk.isEmpty() ? null : tabakitosKlk.remove(tabakitosKlk.size()-1);
    }

    public synchronized Llumi venLlumi() {
        while (!flamisells.isEmpty() && !estaTancat) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return flamisells.isEmpty() ? null : flamisells.remove(flamisells.size()-1);
    }

    public synchronized Paper venPaper() {
        while (!papelillo.isEmpty() && !estaTancat) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return papelillo.isEmpty() ? null : papelillo.remove(flamisells.size()-1);
    }

    public synchronized void addTabac() {
        tabakitosKlk.add(new Tabac());
        System.out.println("Afegint tabac");
        notifyAll();
    }

    public synchronized void addLlumi() {
        flamisells.add(new Llumi());
        System.out.println("Afegint llumí");
        notifyAll();
    }

    public synchronized void addPaper() {
        papelillo.add(new Paper());
        System.out.println("Afegint paper");
        notifyAll();
    }

    public void nouSubministrament() {
        int numeroCalefico = rnd.nextInt(3);
        switch (numeroCalefico) {
            case 0:
                addTabac();
                break;
            case 1:
                addLlumi();
                break;
            case 2:
                addPaper();
                break;
            default:
                break;
        }
    }

    public synchronized void tancarEstanc() {
        System.out.println("Estanc tancat");
        estaTancat = true;
        notifyAll();
    }

    @Override
    public void run() {
        estaTancat = false;
        System.out.println("Estanc obert");
        while (!estaTancat) {
            nouSubministrament();
            try {
                sleep(rnd.nextInt(500, 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}