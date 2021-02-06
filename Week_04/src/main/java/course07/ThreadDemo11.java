package course07;

import java.util.concurrent.ExecutionException;

/**
 * join方法
 *
 * @author JKXAING on 2021/2/6
 */
public class ThreadDemo11 {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Runnable runnable = () -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getRes());
        };

        Thread thread = new Thread(runnable);
        thread.start();

        thread.join();
        System.out.println("main end ...");
    }

    private static String getRes() {
        return "ok";
    }
}
