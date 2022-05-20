package top.iaminlearn.resouce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import top.iaminlearn.resouce.controller.HelloController;

@SpringBootApplication
public class SpringbootResouceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringbootResouceApplication.class, args);
        run.getBean(HelloController.class);
    }

}
