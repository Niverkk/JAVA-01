package course07;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * BlockingQueue
 *
 * @author JKXAING on 2021/2/6
 */
public class ThreadDemo10 {

    private static BlockingQueue blockingQueue = new ArrayBlockingQueue(1);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Runnable runnable = () -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                blockingQueue.put(getRes());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        new Thread(runnable).start();

        System.out.println(blockingQueue.take());
        System.out.println("main end ...");
    }

    private static String getRes() {
        return "ok";
    }
}
