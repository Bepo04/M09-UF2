import java.util.LinkedList;
import java.util.Queue;

public class Barberia extends Thread {
    
    private Queue<Client> salaEspera;
    private int maxCadires;
    private Object condBarber;

    public static Barberia barberia;

    public Barberia(int maxCadires) {
        this.condBarber = new Object();
        this.maxCadires = maxCadires;
        this.salaEspera = new LinkedList<Client>();
    }

    public Object getCondBarber() {
        return condBarber;
    }

    public Client seguentClient() {
        return salaEspera.poll();
    }

    public void entrarClient(Client client) {
        if (salaEspera.size() < maxCadires) {
            synchronized(condBarber) {
                salaEspera.add(client);
                System.out.printf("Client %s en espera%n", client.getNom());
                condBarber.notifyAll();
            }
        } else {
            System.out.printf("No queden cadires, el client %s se'n va amb la cua entre les potes.%n", client.getNom());
        }
    }

    @Override
    public void run() {
        ompleSalaEspera();
        try {
            sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ompleSalaEspera();
    }

    public void ompleSalaEspera() {
        for (int i = 0; i < 10; i++) {
            entrarClient(new Client(i+1));
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        barberia = new Barberia(3);
        Barber barber = new Barber("Pepe");
        barber.start();
        barberia.start();
    }
}
