package com.market.secondhandmarketplace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SecondhandMarketplaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondhandMarketplaceApplication.class, args);
    }

}
