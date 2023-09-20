package cn.trade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TradeRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(TradeRegistryApplication.class, args);
    }

}
