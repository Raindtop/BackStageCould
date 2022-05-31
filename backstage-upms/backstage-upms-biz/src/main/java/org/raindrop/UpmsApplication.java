package org.raindrop;

import org.raindrop.common.security.annotation.EnableSecurityResourceServer;
import org.raindrop.common.swagger.annotation.EnableBackstageSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableBackstageSwagger2
@EnableSecurityResourceServer
@SpringBootApplication
public class UpmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UpmsApplication.class, args);
    }
}
