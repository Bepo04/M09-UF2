public class Compte {

    private static Compte instance;

    private float saldo;
    
    private Compte() {
        this.saldo = 0;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public static Compte getInstance() {

        if (instance == null) {
            instance = new Compte();
        }
        return instance;

    }
}