public class Forquilla {

    private int numero;
    private boolean enUs;
    
    public Forquilla(int numero) {
        this.numero = numero;
        this.enUs = false;
    }

    public int getNumero() {
        return numero;
    }
    public boolean getEnUs() {
        return enUs;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    public void setEnUs(boolean enUs) {
        this.enUs = enUs;
    }
}