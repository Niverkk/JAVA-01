package course07;

import java.util.concurrent.ExecutionException;

/**
 * synchronized 配合标志量
 *
 * @author JKXAING on 2021/2/6
 */
public class ThreadDemo06 {

    private static Object lock = new Object();
    private static boolean flag = false;

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Runnable runnable = () -> {
            synchronized (lock){
                // 循环防止虚假唤醒
                while (flag){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // 睡上一秒
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(getRes());
                ThreadDemo06.flag = true;
                lock.notifyAll();
            }

        };
        new Thread(runnable).start();


        synchronized (lock){
            while (!flag){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.notifyAll();
            }

        }
        System.out.println("main end ...");
    }

    private static String getRes() {
        return "ok";
    }
}
