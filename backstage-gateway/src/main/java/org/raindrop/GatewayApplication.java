package org.raindrop;

import org.raindrop.common.gateway.annotation.EnableDynamicRoute;
import org.raindrop.common.swagger.annotation.EnableBackstageSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDynamicRoute
@EnableBackstageSwagger2
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
