package com.study.camel.simplecamelspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.study.camel.simplecamelspringboot.beans")
public class SimpleCamelSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleCamelSpringBootApplication.class, args);
	}

}
