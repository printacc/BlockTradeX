package cn.muses.trade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TradeAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(TradeAdminApplication.class, args);
    }

}
