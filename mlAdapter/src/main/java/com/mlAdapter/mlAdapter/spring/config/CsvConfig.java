package com.mlAdapter.mlAdapter.spring.config;

import com.mlAdapter.mlAdapter.controller.ControllerCsv;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;
@Slf4j
@Profile("Csv-file")
@Configuration
public class CsvConfig
{
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };


    }
    @Bean
    public ControllerCsv controllerCsv(){

        return new ControllerCsv();
    }



}
