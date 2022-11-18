package com.shop.campaign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CampaignApplication {

    Logger logger = LoggerFactory.getLogger(CampaignApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CampaignApplication.class, args);
    }


}
