import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Coet {

    private Motor motor1;
    private Motor motor2;
    private Motor motor3;
    private Motor motor4;

    public Coet() {
        this.motor1 = new Motor();
        this.motor2 = new Motor();
        this.motor3 = new Motor();
        this.motor4 = new Motor();
    }

    public void arranca() {
        motor1.start();
        motor2.start();
        motor3.start();
        motor4.start();
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

    public static void main(String[] args) {

        Coet coet = new Coet();
        int potencia = readLine();
        coet.passaAPotencia(potencia);
        coet.arranca();
        while (potencia != 0) {
            potencia = readLine();
            coet.passaAPotencia(potencia);

        } 
    }

    public static int readLine() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String resposta;
        int potencia = 0;
        try {
            resposta = reader.readLine();
            potencia = Integer.parseInt(resposta);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("ERROR: has d'introduïr un valor enter");
            return -1;
        }
        return potencia;
    }
}
