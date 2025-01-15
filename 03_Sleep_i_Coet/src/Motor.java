public class Motor {
    
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

    public void setPotencia(int potencia) {
        System.out.println("Passant a potència " + potencia);
        potenciaObjectiu = potencia;
        while (!(potenciaActual == potenciaObjectiu)) {
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
    }

    @Override
    public String toString() {
        String estat = potenciaActual == potenciaObjectiu ? "FerRes" : potenciaActual > potenciaObjectiu ? "Decre." : "Incre." ;
        return String.format("Motor %d: %s Objectiu: %d Actual: %d", this.id, estat, potenciaObjectiu, potenciaActual);
    }

    public static void main(String[] args) {
        Motor motor = new Motor();
        motor.setPotencia(10);
        System.out.println("Acabat primer canvi");
        motor.setPotencia(0);
    }
}