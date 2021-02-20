package info.niverkk.course09.work02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.inject.Inject;

/**
 * @author JKXAING on 2021/2/20
 */
@Component
public class DemoOwner {

    // 1.默认按类型装配，容器中只有相应类型的一个对象时可单独使用
    //@Autowired
    private Demo demo1;

    // 2.搭配Qualifier可指定名称
    @Autowired
    @Qualifier("demo2")
    private Demo demo2;

    // 3.效果类似Autowired + Qualifier
    @Resource(name = "demo3")
    private Demo demo3;

    // 6.
    @Inject
    @Qualifier("demo3")
    private Demo demo4;


    private Demo demo5;

    public DemoOwner() {

    }

    // 4.Autowired放在构造器上,此时不能搭配Qualifier
    //@Autowired
    public DemoOwner(Demo demo4) {
        this.demo4 = demo4;
    }

    // 5.Autowired放在set方法上,此时不能搭配Qualifier
    //@Autowired
    public void setDemo5(Demo demo5) {
        this.demo5 = demo5;
    }

    @Override
    public String toString() {
        return "DemoOwner{" +
                "demo1=" + demo1 +
                ", demo2=" + demo2 +
                ", demo3=" + demo3 +
                ", demo4=" + demo4 +
                ", demo5=" + demo5 +
                '}';
    }
}
