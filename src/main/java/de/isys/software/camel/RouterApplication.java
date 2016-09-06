package de.isys.software.camel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Standalone Application for Apache Camel
 */
@SpringBootApplication
@ImportResource("classpath:spring.xml")
public class RouterApplication {
	public static void main(String[] args) {
		SpringApplication.run(RouterApplication.class, args);
	}
}