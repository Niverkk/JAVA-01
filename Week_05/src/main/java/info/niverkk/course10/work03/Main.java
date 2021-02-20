package info.niverkk.course10.work03;

import info.niverkk.mystarter.HelloService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author JKXAING on 2021/2/20
 */
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);

        // 通过自定义starter拿到里面的bean
        // 主要原理 org.springframework.boot.autoconfigure.AutoConfigurationImportSelector.getCandidateConfigurations
        // 自定义starter需要有指定文件 META-INF/spring.factories
        // 按照对应格式写,就能加载到
        HelloService bean = context.getBean(HelloService.class);
        System.out.println(bean);
    }
}
