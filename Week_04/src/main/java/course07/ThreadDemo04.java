package course07;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;

/**
 * CyclicBarrier
 *
 * @author JKXAING on 2021/2/6
 */
public class ThreadDemo04 {

    public static void main(String[] args) throws ExecutionException, InterruptedException, BrokenBarrierException {
        CyclicBarrier barrier = new CyclicBarrier(2);

        Runnable runnable = () -> {
            // 睡上一秒
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(getRes());

            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        };
        new Thread(runnable).start();

        barrier.await();
        System.out.println("main end ...");
    }

    private static String getRes() {
        return "ok";
    }


}
