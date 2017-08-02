package com.assetman.fiaame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;

@SpringBootApplication
public class SpringBootWebApplication {

    private static final Logger log = LoggerFactory.getLogger(SpringBootWebApplication.class);

    @PostConstruct
    public void initApplication() throws IOException {
        log.info("Running with Spring profile(s) : {}");
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringBootWebApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}
