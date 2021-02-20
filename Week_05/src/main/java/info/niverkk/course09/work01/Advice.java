package info.niverkk.course09.work01;

/**
 * @author JKXAING on 2021/2/20
 */
public class Advice {

    public static void beforeAdvice() {
        System.out.println("beforeAdvice ... ");
    }

    public static void afterAdvice() {
        System.out.println("afterAdvice ... ");
    }

    public static void exceptionAdvice() {
        System.out.println("exceptionAdvice ... ");
    }
}
