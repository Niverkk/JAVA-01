package info.niverkk.course09.work02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author JKXAING on 2021/2/20
 */
@Configuration
public class MyConfigruation {
    @Bean
    public Demo demo1() {
        return new Demo("demo1");
    }

    @Bean
    public Demo demo2() {
        return new Demo("demo2");
    }

    @Bean
    public Demo demo3() {
        return new Demo("demo3");
    }

}
