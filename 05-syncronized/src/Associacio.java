public class Associacio {
    
    private int numSocis;
    private Soci[] socis;

    public Associacio() {
        this.numSocis = 1000;
        this.socis = new Soci[this.numSocis];
    }

    public void iniciaCompteTempsSocis() {
        
        for (int i = 0; i < socis.length; i++) {
            socis[i] = new Soci();
            socis[i].start();
        }
    }

    public void esperaPeriodeSocis() {
        for (Soci soci : this.socis) {
            try {
                soci.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void mostraBalancComptes() {
        float saldo = Compte.getInstance().getSaldo();
        System.out.printf("Saldo: %.2f%n", saldo);
    }

    public static void main(String[] args) {
        Associacio aso = new Associacio();
        aso.iniciaCompteTempsSocis();
        aso.esperaPeriodeSocis();
        aso.mostraBalancComptes();
    }
}