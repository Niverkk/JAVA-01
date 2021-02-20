package info.niverkk.course09.work02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 一.注解主要是三种
 * 1.@Autowired,可搭配@Qualifier
 * 2.@Resource
 * 3.@Inject,可搭配@Qualifier
 * <p>
 * 看注解作用范围,可用在例如属性,set方法,构造方法上
 * <p>
 * 二.xml
 * 类似属性注入,构造方法注入等
 *
 * @author JKXAING on 2021/2/20
 */
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("info.niverkk.course09.work02");

        DemoOwner bean = context.getBean(DemoOwner.class);
        System.out.println(bean);
    }
}
