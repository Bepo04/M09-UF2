public class Fil extends Thread {

    private int count;

    public Fil() {
        super();
        this.count = 0;
    }

    public Fil(String nomFil) {
        super(nomFil);
        this.count = 0;
    }
    
    @Override
    public void run() {
        
        for (int i = 0; i < 10; i++) {
            this.count++;
            System.out.printf("%s %d \n", this.getName(), this.count);
        }

        System.out.printf("Acaba el thread %s\n", this.getName());
    }
}