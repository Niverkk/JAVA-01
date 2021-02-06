package course07;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * futureTask.get()阻塞任务
 *
 * @author JKXAING on 2021/2/6
 */
public class ThreadDemo01 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<String> futureTask = new FutureTask<>(() -> {
            // 睡上一秒
            Thread.sleep(1000);
            return "ok";
        });
        new Thread(futureTask).start();

        System.out.println(futureTask.get());
        System.out.println("main end ...");
    }
}
