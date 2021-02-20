package info.niverkk.course09.work01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理对象都会调用InvocationHandler的invoke方法
 *
 * @author JKXAING on 2021/2/20
 */
public class MyInvocationHandler implements InvocationHandler {
    // 目标对象
    private Target target;

    public MyInvocationHandler(Target target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 类似前置通知
        Advice.beforeAdvice();

        // 执行目标方法
        Object res = method.invoke(target, args);

        // 类似后置通知，实际不是这样
        Advice.beforeAdvice();
        return res;
    }
}
