public class Coet {

    private Motor motor1;
    private Motor motor2;
    private Motor motor3;
    private Motor motor4;

    public void arranca() {
        this.motor1 = new Motor();
        this.motor2 = new Motor();
        this.motor3 = new Motor();
        this.motor4 = new Motor();
    }

    public boolean passaAPotencia(int potencia) {
        if (potencia < 0 || potencia > 10) {
            System.out.println("ERROR: La velocitat ha de ser un valor entre 0 i 10");
            return false;
        }

        this.motor1.setPotencia(potencia);
        this.motor2.setPotencia(potencia);
        this.motor3.setPotencia(potencia);
        this.motor4.setPotencia(potencia);
        
        return true;
    }
}
