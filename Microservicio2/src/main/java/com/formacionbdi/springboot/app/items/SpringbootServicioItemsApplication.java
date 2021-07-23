package com.formacionbdi.springboot.app.items;

import com.formacionbdi.springboot.app.items.decoder.StatusErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableHystrix
@EnableHystrixDashboard
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class SpringbootServicioItemsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootServicioItemsApplication.class, args);
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new StatusErrorDecoder();
    }
}

