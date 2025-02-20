import java.util.concurrent.locks.ReentrantLock;

public class Forquilla {

    private int numero;
    private ReentrantLock bloqueig;

    public Forquilla(int numero) {
        this.numero = numero;
        this.bloqueig = new ReentrantLock(true);
    }   

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void agafar() {
        bloqueig.lock();
    }

    public void deixar() {
        if (bloqueig.isHeldByCurrentThread()) {
            bloqueig.unlock();
        }
    }

}