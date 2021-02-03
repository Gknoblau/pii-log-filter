package dev.knoblauch.piilog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class PiiLogApplication {

	public static void main(String[] args) {
		SpringApplication.run(PiiLogApplication.class, args);
	}

}
