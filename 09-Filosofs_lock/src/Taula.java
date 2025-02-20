import java.util.ArrayList;

public class Taula {
    
    private ArrayList<Filosof> comensals;
    private ArrayList<Forquilla> forquilles;

    public Taula(int numero) {
        creaForquilles(numero);
        creaFilosofs(numero);
    }

    private void creaForquilles(int numForquilles) {
        forquilles = new ArrayList<Forquilla>();
        for (int i = 0; i < numForquilles; i++) {
            forquilles.add(new Forquilla(i));
        }
    }

    private void creaFilosofs(int numFilosofs) {
        comensals = new ArrayList<Filosof>();
        for (int i = 0; i < numFilosofs; i++) {
            Forquilla dreta = i == numFilosofs -1 ? forquilles.get(0) : forquilles.get(i + 1);
            Forquilla esquerra = forquilles.get(i);
            Filosof filosof = new Filosof(i, "fil" + i, dreta, esquerra);
            comensals.add(filosof);
        }
    }

    private void showTaula() {
        for (Filosof filosof : comensals) {
            Forquilla esquerra = filosof.getForquillaEsquerra();
            Forquilla dreta = filosof.getForquillaDreta();
            System.out.printf("Comensal: %s esq: %d dret: %d%n", filosof.getName(), esquerra.getNumero(), dreta.getNumero());
        }
    }

    private void cridarATaula() {
        for (Filosof filosof : comensals) {
            filosof.start();
        }
    }

    public static void main(String[] args) {
        Taula taula = new Taula(4);
        taula.showTaula();
        taula.cridarATaula();
    }
}