import java.util.ArrayList;

public class Organitzador {

    Esdeveniment esdeveniment;

    ArrayList<Assistent> assistents;


    public Organitzador(Esdeveniment esdeveniment) {
        this.esdeveniment = esdeveniment;
        this.assistents = this.iniciaAssistents();
    }


    public static void main(String[] args) {
        
        Organitzador organitzador = new Organitzador(new Esdeveniment(5));

        for (Assistent assistent : organitzador.assistents) {
            assistent.start();
        }
    }

    public ArrayList<Assistent> iniciaAssistents() {
        ArrayList<Assistent> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Assistent("Assistent-" + i, this.esdeveniment));
        }
        return list;
    }
}
