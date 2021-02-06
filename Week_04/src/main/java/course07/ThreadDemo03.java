package course07;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * CountDownLatch
 *
 * @author JKXAING on 2021/2/6
 */
public class ThreadDemo03 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Runnable runnable = () -> {
            // 睡上一秒
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(getRes());
            countDownLatch.countDown();

        };
        new Thread(runnable).start();

        countDownLatch.await();
        System.out.println("main end ...");
    }

    private static String getRes() {
        return "ok";
    }


}
