public class DormAleatori extends Thread {

    private long creat;

    public DormAleatori(String name) {
        super(name);
        this.creat = System.currentTimeMillis();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            long randomNumber = (long)(Math.random() * 1000 + 1);
            long tempsActual = System.currentTimeMillis() - creat;
            System.out.printf("Joan(%d) a dormir %d ms total\t%d%n", i, randomNumber, tempsActual);
            try {
                sleep(randomNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        DormAleatori joan = new DormAleatori("Joan");
        DormAleatori pep = new DormAleatori("Pep");
        joan.start();
        pep.start();
        System.out.println("-- Fi del Main -------------------------");
    }
}