package course07;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 配合sleep，不严谨
 *
 * @author JKXAING on 2021/2/6
 */
public class ThreadDemo08 {

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    private static boolean flag = false;


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Runnable runnable = () -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lock.lock();
            try {
                while (flag){
                    condition.await();
                }
                Thread.sleep(3000);
                System.out.println(getRes());

                flag = true;
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        };
        new Thread(runnable).start();


        lock.lock();
        try {
            while (!flag){
                condition.await();
            }
            condition.signalAll();
        } finally {
            lock.unlock();
        }
        System.out.println("main end ...");
    }

    private static String getRes() {
        return "ok";
    }
}
