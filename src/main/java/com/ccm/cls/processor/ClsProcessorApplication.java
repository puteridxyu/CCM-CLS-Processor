package com.ccm.cls.processor;

import com.ccm.cls.processor.service.FileProcessorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClsProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClsProcessorApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(FileProcessorService fileProcessorService) {
        return args -> {
            String filePath = "src/main/resources/input.txt";
            fileProcessorService.processFile(filePath);
        };
    }
}

