public class Client extends Barberitat {
    
    private String nom;

    public Client(int id) {
        this.nom = "Client - " + id;
    }

    public void tallarseElCabell() {
        System.out.printf("Tallant cabell a %s.%n", nom);
        System.err.println(nom + " Opina: " + this.quinaBarberitat());
    }

    public String getNom() {
        return nom;
    }
}
