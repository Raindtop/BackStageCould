package org.raindrop;

import org.raindrop.common.swagger.annotation.EnableBackstageSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableBackstageSwagger2
@EnableFeignClients
@SpringBootApplication(scanBasePackages={"org.raindrop"})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
