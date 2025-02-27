import java.util.ArrayList;
import java.util.Random;

public class Estanc {

    private ArrayList<Tabac> tabakitosKlk;
    private ArrayList<Llumi> flamisells;
    private ArrayList<Paper> papelillo;
    
    private Random calef; 

    public Estanc() {
        this.tabakitosKlk = new ArrayList<Tabac>();
        this.flamisells = new ArrayList<Llumi>();
        this.papelillo = new ArrayList<Paper>();
    }

    public Tabac venTabac() {
        if (!tabakitosKlk.isEmpty()) {
            return tabakitosKlk.remove(tabakitosKlk.size()-1);
        }
        else return null;
    }

    public Llumi venLlumi() {
        if (!flamisells.isEmpty()) {
            return flamisells.remove(flamisells.size()-1);
        }
        else return null;
    }

    public Paper venPaper() {
        if (!papelillo.isEmpty()) {
            return papelillo.remove(flamisells.size()-1);
        }
        else return null;
    }

    public void addTabac() {
        tabakitosKlk.add(new Tabac());
    }

    public void addLlumi() {
        flamisells.add(new Llumi());
    }

    public void addPaper() {
        papelillo.add(new Paper());
    }

    public void nouSubministrament() {
        int numeroCalefico = calef.nextInt(3);
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

    public boolean tancarEstanc() {
        return true;
    }

    public static void main(String[] args) {

        Estanc estanc = new Estanc();
        boolean  estaTancat = false;
        while (!estaTancat) {
            estanc.nouSubministrament();
        }
    }
}