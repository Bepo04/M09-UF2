import java.util.Random;

public class Dona extends Thread {

    private BanyUnisex bany;
    private Random rnd;

    public Dona(String name, BanyUnisex bany) {
        super(name);
        this.bany = bany;
        this.rnd = new Random();

    }

    @Override
    public void run() {
        entraDona();
        utilitzaLababo();
        surtDona();
    }

    public void entraDona() {

    }

    public void utilitzaLababo() {
        
    }

    public void surtDona() {

    }
}