package com.assetman.fiaame;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootWebApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringBootWebApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}
