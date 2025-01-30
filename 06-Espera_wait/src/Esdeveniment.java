import java.util.ArrayList;
import java.util.List;

public class Esdeveniment {

    private List<Assistent> assistents;
    private int placesDisponibles;

    public Esdeveniment(int maxPlaces) {
        this.assistents = new ArrayList<Assistent>();
        if (maxPlaces >= 1) this.placesDisponibles = maxPlaces;
        else this.placesDisponibles = 10;
    }

    public synchronized void ferReserva(Assistent assistent) {
        while (placesDisponibles <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (placesDisponibles > 0 && obtePosicioAssistent(assistent.getName()) < 0) {
            this.assistents.add(assistent);
            placesDisponibles--;
            System.out.printf("%s ha fet una reserva. Places disponibles: %d%n", assistent.getName(), placesDisponibles);
        }
    }

    public synchronized void cancelaReserva(Assistent assistent) {
        int posicio = obtePosicioAssistent(assistent.getName());
        if (posicio > -1) {
            assistents.remove(posicio);
            placesDisponibles++;
            notifyAll();
            System.out.printf("%s ha cancel·lat una reserva. Places disponibles: %d%n", assistent.getName(), placesDisponibles);
        } else {
            System.out.printf("%s no ha pogut cancel·lar una reserva inexistent. Places disponibles: %d%n", assistent.getName(), placesDisponibles);
        }
    }

    /*
     * Retorna la posició a la llista del nom d'assistent rebut. Retorna l'index
     * o -1 si no existeix 
     */
    private int obtePosicioAssistent(String nomAssistent) {
        int i = 0;
        for (Assistent currentAssist : assistents) {
            if (currentAssist.getName().equals(nomAssistent)) {
                return i;
            }
            i++;
        }
        return -1;
    }
}