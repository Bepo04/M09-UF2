public class Motor extends Thread {
    
    private static int incrementId = 0;

    private int potenciaObjectiu;
    private int potenciaActual;
    private int id;
    private boolean canviant;

    public Motor() {
        this.potenciaObjectiu = 0;
        this.potenciaActual = 0;
        incrementId++;
        this.id = incrementId;
        this.canviant = false;
    }
    /*
     * passar de la potència actual a la objectiu en passos de 1 (incrementant o decrementant) i per
     * simular el comportament d’un motor real trigarà en fer-ho un interval aleatori de entre 1 i 2
     * segons.
     */
    public void setPotencia(int potencia) {
        this.potenciaObjectiu = potencia;
    }

    public void setCanviant(boolean canvi) {
        this.canviant = canvi;
    }

    @Override
    public void run() {
        
        while (canviant) {
            boolean mesGran = potenciaObjectiu > potenciaActual;
            try {
                int delay = 1000 + (int) (Math.random() * 1000);
                Thread.sleep(delay);
                potenciaActual += mesGran ?  1 : -1;
            } catch (InterruptedException e) {
                System.out.println(e.getLocalizedMessage());
            }
            System.out.println(this.toString());
            if (potenciaActual == potenciaObjectiu) {
                canviant = false;
            }
        }
    }

    @Override
    public String toString() {
        String estat = potenciaActual == potenciaObjectiu ? "FerRes" : potenciaActual > potenciaObjectiu ? "Decre." : "Incre." ;
        return String.format("Motor %d: %s Objectiu: %d Actual: %d", this.id, estat, potenciaObjectiu, potenciaActual);
    }
}