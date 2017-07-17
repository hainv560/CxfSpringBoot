package com.example.cxf.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = { "com.example.cxf.springboot.service.*" ,"com.example.cxf.springboot.ws.*"})
@EntityScan(basePackages = { "com.example.cxf.springboot.model.*" })
@EnableJpaRepositories(basePackages = { "com.example.cxf.springboot.repository.*" })
public class CxfSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(CxfSpringbootApplication.class, args);
	}
}
