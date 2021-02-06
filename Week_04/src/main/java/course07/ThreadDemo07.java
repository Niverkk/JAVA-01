package course07;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 配合sleep，不严谨
 *
 * @author JKXAING on 2021/2/6
 */
public class ThreadDemo07 {

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Runnable runnable = () -> {
            lock.lock();
            // 睡上一秒
            try {
                Thread.sleep(3000);
                System.out.println(getRes());

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        };
        new Thread(runnable).start();

        // 睡眠保证让子线程先拿到锁
        Thread.sleep(100);

        lock.lock();
        // 睡上一秒
        try {

        } finally {
            lock.unlock();
        }
        System.out.println("main end ...");
    }

    private static String getRes() {
        return "ok";
    }
}
