package nju.se;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


// @WebFilter注解是Servlet3.0的规范，并不是Spring boot提供的。除了这个注解以外，我们还需在配置类中加另外一个注解：@ServletComponetScan，指定扫描的包
@ServletComponentScan("nju.se.config")
@SpringBootApplication
public class HotWaterApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotWaterApplication.class, args);
    }

}
