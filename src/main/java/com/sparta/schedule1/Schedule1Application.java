package com.sparta.schedule1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Schedule1Application {

	public static void main(String[] args) {
		SpringApplication.run(Schedule1Application.class, args);
	}

}
