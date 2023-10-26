package com.marolix.Bricks99;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@SpringBootApplication
@PropertySource("classpath:messages.properties")
public class Bricks99Application {

	public static void main(String[] args) {

			SpringApplication.run(Bricks99Application.class, args);
	}

}
