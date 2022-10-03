package com.ayi.trabajo_final.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
@EntityScan(basePackages = { "com.ayi.trabajo_final.app.entities" })
@EnableJpaRepositories(basePackages = { "com.ayi.trabajo_final..app.repositories" })
public class ayi_trabajoFinal_app {

	public static void main(String[] args) {

		SpringApplication.run(ayi_trabajoFinal_app.class, args);
	}

}
