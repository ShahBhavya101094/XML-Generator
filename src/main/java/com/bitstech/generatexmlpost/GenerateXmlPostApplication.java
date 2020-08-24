package com.bitstech.generatexmlpost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class GenerateXmlPostApplication {

    public static void main(String[] args) {
        SpringApplication.run(GenerateXmlPostApplication.class, args);
    }

}
