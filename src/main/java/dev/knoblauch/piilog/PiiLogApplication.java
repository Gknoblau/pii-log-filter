package dev.knoblauch.piilog;

import dev.knoblauch.piilog.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class PiiLogApplication {

	public static void main(String[] args) {
		SpringApplication.run(PiiLogApplication.class, args);
	}

}
