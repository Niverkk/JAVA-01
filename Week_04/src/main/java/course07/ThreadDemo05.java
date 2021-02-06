package course07;

import java.util.concurrent.ExecutionException;

/**
 * synchronized 配合sleep，不严谨
 *
 * @author JKXAING on 2021/2/6
 */
public class ThreadDemo05 {

    private static Object lock = new Object();

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Runnable runnable = () -> {
            synchronized (lock){
                // 睡上一秒
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(getRes());

                lock.notifyAll();
            }

        };
        new Thread(runnable).start();

        // 睡眠保证让子线程先拿到锁
        Thread.sleep(100);

        synchronized (lock){

        }
        System.out.println("main end ...");
    }

    private static String getRes() {
        return "ok";
    }
}
