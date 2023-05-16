package de.woock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SchufaApp {

	public static void main(String[] args) {
		SpringApplication.run(SchufaApp.class, args);
	}
}
