package course07;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * while (!futureTask.isDone()) 循环判断，不阻塞线程
 *
 * @author JKXAING on 2021/2/6
 */
public class ThreadDemo02 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<String> futureTask = new FutureTask<>(() -> {
            // 睡上一秒
            Thread.sleep(1000);
            return "ok";
        });
        new Thread(futureTask).start();

        while (!futureTask.isDone()) {

        }
        System.out.println("main end ...");
    }
}
