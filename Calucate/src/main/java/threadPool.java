import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class threadPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newWorkStealingPool();
        System.out.println("test");
    }
}
