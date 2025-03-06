package com.monitoring.log_storing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LogStoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogStoringApplication.class, args);
	}

}
