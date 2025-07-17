package org.example.projecttest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "org.example")
@MapperScan("org.example.projecttest.repository")
@EntityScan("org.example.entity")
public class ProjectTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectTestApplication.class, args);
    }

}
