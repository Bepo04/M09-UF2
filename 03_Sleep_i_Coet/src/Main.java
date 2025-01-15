import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Coet coet = new Coet();

        int potencia = 0;
        do {
            String resposta;
            try {
                resposta = reader.readLine();
                potencia = Integer.parseInt(resposta);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("ERROR: has d'introduïr un valor enter");
                continue;
            }
            
            if (!coet.passaAPotencia(potencia)) continue;
            coet.arranca();
        } while (potencia > 0);
    }
}