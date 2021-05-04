package com.mlAdapter.mlAdapter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


@Slf4j
@SpringBootApplication
public class MlAdapterApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MlAdapterApplication.class).profiles().run();
    }

}
