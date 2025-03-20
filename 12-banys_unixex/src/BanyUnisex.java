import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class BanyUnisex {
    

    private final int CAPACITAT_MAX = 3;
    
    private static final int BANY_BUIT = 0;
    private static final int BANY_AMB_HOMES = 1;
    private static final int BANY_AMB_DONES = 2;

    private int estatActual;
    private int ocupants;
    private Semaphore capacitat;
    private ReentrantLock lockEstat;

    public BanyUnisex() {
        this.estatActual = BANY_BUIT;
        this.ocupants = 0;
        this.capacitat = new Semaphore(CAPACITAT_MAX, true);
        this.lockEstat = new ReentrantLock(true);    
    }

    public void entraHome() {
        if (estatActual == BANY_BUIT || estatActual == BANY_AMB_HOMES) {
            estatActual = BANY_AMB_HOMES;
            try {
                capacitat.tryAcquire(BANY_AMB_DONES, null);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }
}
