import java.util.Random;

public class Home extends Thread {

    private BanyUnisex bany;
    private Random rnd;

    public Home(String name, BanyUnisex bany) {
        super(name);
        this.bany = bany;
        this.rnd = new Random();

    }

    @Override
    public void run() {
        entraHome();
        utilitzaLababo();
        surtHome();
    }

    public void entraHome() {
        
    }

    public void utilitzaLababo() {
        
    }

    public void surtHome() {
        try {
            sleep(rnd.nextInt(2000, 3000));
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}