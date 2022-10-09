import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class threadPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newWorkStealingPool();
        System.out.println("test");

        Random random = new Random();
        int i = 0;
        while (i < 50) {
            String rundonStr = "";
            while (true) {
                rundonStr = String.valueOf(random.nextInt(1000));
                if (rundonStr.length() == 3) {
                    break;
                }
            }
            String reqId = System.currentTimeMillis() + rundonStr;
            System.out.println(reqId);
            i++;
        }
    }
}
