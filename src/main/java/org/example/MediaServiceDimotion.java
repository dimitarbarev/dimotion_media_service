package org.example;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class MediaServiceDimotion {
    public static void main(String[] args) {
        SpringApplication.run(MediaServiceDimotion.class, args);
    }
}
