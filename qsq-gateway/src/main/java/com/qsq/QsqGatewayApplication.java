package com.qsq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lenovo
 */
@SpringBootApplication
public class QsqGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(QsqGatewayApplication.class, args);
    }



}
