package com.ayi.trabajo_final.app.configuration;

// Packages and classes to import of springframework 5.x
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages={"com.ayi.trabajo_final.app.configuration" +
        "com.ayi.trabajo_final..app.service" +
        "com.ayi.trabajo_final.app.mapper" +
        "com.ayi.trabajo_final.app.repository"})
public class CommonsConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}