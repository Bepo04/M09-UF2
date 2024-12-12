public class Principal {

    public static void main(String[] args) {
        
        Fil juan = new Fil("Juan");
        juan.setPriority(10);

        Fil pepe = new Fil("Pepe");
        pepe.setPriority(1);
        
        juan.start();
        pepe.start();

        System.out.println("Acaba thread Main");
        
    }
}