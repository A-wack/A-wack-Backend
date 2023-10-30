package com.example.musicrequestapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class MusicRequestAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicRequestAppApplication.class, args);
    }

}
