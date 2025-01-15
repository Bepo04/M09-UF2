import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Coet coet = new Coet();
        coet.arranca();

        do {
            System.out.println("Introdueix la potència");
            String resposta;
            int potencia;
            try {
                resposta = reader.readLine();
                potencia = Integer.parseInt(resposta);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("ERROR: has d'introduïr un valor enter");
                continue;
            }
        } while (true);
    }
}
