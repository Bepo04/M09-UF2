import java.util.ArrayList;

public class Organitzador {
    
    public static void main(String[] args) {
        
        Esdeveniment esdeveniment = new Esdeveniment(5);

        ArrayList<Assistent> assistents = iniciaAssistents(esdeveniment);

        for (Assistent assistent : assistents) {
            assistent.start();
        }
    }

    public static ArrayList<Assistent> iniciaAssistents(Esdeveniment esdeveniment) {
        ArrayList<Assistent> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Assistent("Assistent-" + i, esdeveniment));
        }
        return list;
    }

}
