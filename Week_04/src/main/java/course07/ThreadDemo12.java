package course07;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicStampedReference;

import static java.lang.Thread.MAX_PRIORITY;
import static java.lang.Thread.yield;

/**
 * yield
 *
 * @author JKXAING on 2021/2/6
 */
public class ThreadDemo12 {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Runnable runnable = () -> {
            System.out.println(getRes());
        };

        Thread thread = new Thread(runnable);
        thread.setPriority(MAX_PRIORITY);
        thread.start();

        //优先让给优先级高的,单cpu会有效果
        yield();
        System.out.println(Thread.currentThread().getPriority());
        System.out.println("main end ...");

    }

    private static String getRes() {
        return "ok";
    }
}
