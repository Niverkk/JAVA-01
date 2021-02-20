package info.niverkk.course09.work01;

import java.lang.reflect.Proxy;

/**
 * 使用java的动态代理，实现一个简单的aop
 *
 * @author JKXAING on 2021/2/20
 */
public class Main {

    public static void main(String[] args) {
        Target target = new TargetImpl();

        Class<TargetImpl> targetClass = TargetImpl.class;
        MyInvocationHandler h = new MyInvocationHandler(target);
        Target proxy = (Target) Proxy.newProxyInstance(targetClass.getClassLoader(), targetClass.getInterfaces(), h);

        proxy.print();
    }
}


