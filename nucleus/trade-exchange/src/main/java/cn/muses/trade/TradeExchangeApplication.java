package cn.muses.trade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TradeExchangeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TradeExchangeApplication.class, args);
    }

}
