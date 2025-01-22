public class Administracio {
    
    private static final int num_poblacio = 50;

    private Treballador[] poblacioActiva;
    public Administracio() {
        creapoblacioActiva();
    }

    private void creapoblacioActiva() {
        this.poblacioActiva = new Treballador[num_poblacio];
        for (int i = 0; i < poblacioActiva.length; i++) {
            this.poblacioActiva[i] = new Treballador(
                "Ciutadà-" + i , 
                25000, 
                20, 
                65
            );
        }
    }

    public static void main(String[] args) {
        
        Administracio admin = new Administracio();

        for (Treballador treb : admin.poblacioActiva) {
            treb.start();
        }

        for (Treballador treb : admin.poblacioActiva) {
            try {
                treb.join();
                System.out.print(treb.toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }
}
