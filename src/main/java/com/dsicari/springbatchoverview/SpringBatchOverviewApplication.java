package com.dsicari.springbatchoverview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan({"com.dsicari.springbatchoverview"})
@ComponentScan("com.dsicari.springbatchoverview")
public class SpringBatchOverviewApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchOverviewApplication.class, args);
    }

}
