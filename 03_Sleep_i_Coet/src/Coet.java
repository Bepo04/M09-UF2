public class Coet {

    private Motor motor1;
    private Motor motor2;
    private Motor motor3;
    private Motor motor4;
    private boolean arrancat;

    public Coet() {
        this.motor1 = new Motor();
        this.motor2 = new Motor();
        this.motor3 = new Motor();
        this.motor4 = new Motor();
    }

    public void arranca() {
        if (!arrancat) {
            motor1.start();
            motor2.start();
            motor3.start();
            motor4.start();
            arrancat = true;
        }
        
    }

    /*
     * Comprova que la potència és correcte i estableix la potència als motors
     */
    public boolean passaAPotencia(int potencia) {

        if (potencia < 0 || potencia > 10) {
            System.out.println("ERROR: La velocitat ha de ser un valor entre 0 i 10");
            return false;
        }

        System.out.println("Passant a potència " + potencia);
        
        this.motor1.setPotencia(potencia);
        this.motor2.setPotencia(potencia);
        this.motor3.setPotencia(potencia);
        this.motor4.setPotencia(potencia);

        return true;
    }
}
