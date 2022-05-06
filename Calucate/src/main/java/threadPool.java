import java.util.concurrent.Executors;

public class threadPool {
    public static void main(String[] args) {
        Executors.newWorkStealingPool();
    }
}
