package com.droptable.tipsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TipsserviceApplication {
//
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(TipsserviceApplication.class);
        app.setWebApplicationType(WebApplicationType.REACTIVE);
        app.run(args);
//        SpringApplication.run(TipsserviceApplication.class, args);
    }
}
