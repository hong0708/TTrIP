package com.ttrip.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "com.ttrip.core.repository")
@EnableMongoRepositories(basePackages = "com.ttrip.core.mongo")
public class TtripApplication {
	public static void main(String[] args) {
		SpringApplication.run(TtripApplication.class, args);
	}
}
