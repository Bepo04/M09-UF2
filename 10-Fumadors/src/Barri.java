public class Barri {
    
    private Estanc estanc;
    private Fumador[] fumadors;

    public Barri() {
        estanc = new Estanc();
        this.fumadors = new Fumador[3];
        creaFumadors();
    }

    public void creaFumadors() {
        fumadors[0] = new Fumador(0, estanc);
        fumadors[1] = new Fumador(1, estanc);
        fumadors[2] = new Fumador(2, estanc);
    }

    public void iniciaFumadors() {
        for (Fumador fumador : fumadors) {
            fumador.start();
        }
    }

    public static void main(String[] args) {
        Barri barri = new Barri();
        barri.iniciaFumadors();
        barri.estanc.start();

        
    }

}
