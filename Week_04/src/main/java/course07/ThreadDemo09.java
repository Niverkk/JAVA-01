package course07;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 循环判断
 *
 * @author JKXAING on 2021/2/6
 */
public class ThreadDemo09 {

    private static Object obj = new Object();
    private static AtomicReference ar = new AtomicReference(obj);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Runnable runnable = () -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Object res = getRes(obj);
            ar.set(res);
        };

        new Thread(runnable).start();

        System.out.println(obj.hashCode());
        //
        while (ar.get() == obj){

        }
        System.out.println(ar.get().hashCode());
        System.out.println("main end ...");
    }

    private static Object getRes(Object obj) {
        return new Object();
    }
}
