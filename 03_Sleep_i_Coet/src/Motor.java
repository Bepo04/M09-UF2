public class Motor extends Thread {
    
    private static int incrementId = 0;

    private int potenciaObjectiu;
    private int potenciaActual;
    private int id;

    public Motor() {
        this.potenciaObjectiu = 0;
        this.potenciaActual = 0;
        incrementId++;
        this.id = incrementId;
    }
    /*
     * passar de la potència actual a la objectiu en passos de 1 (incrementant o decrementant) i per
     * simular el comportament d’un motor real trigarà en fer-ho un interval aleatori de entre 1 i 2
     * segons.
     */
    public void setPotencia(int potencia) {
        this.potenciaObjectiu = potencia;
    }

    @Override
    public void run() {
        while (true) {
            while (potenciaActual != potenciaObjectiu) {
                boolean mesGran = potenciaObjectiu > potenciaActual;
                try {
                    int delay = 1000 + (int) (Math.random() * 1000);
                    Thread.sleep(delay);
                    potenciaActual += mesGran ?  1 : -1;
                } catch (InterruptedException e) {
                    System.out.println(e.getLocalizedMessage());
                }
                System.out.println(this.toString());
            }
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (potenciaActual == 0) break;
        }
    }

    @Override
    public String toString() {
        String estat = potenciaActual == potenciaObjectiu ? "FerRes" : potenciaActual > potenciaObjectiu ? "Decre." : "Incre." ;
        return String.format("Motor %d: %s Objectiu: %d Actual: %d", this.id, estat, potenciaObjectiu, potenciaActual);
    }
}